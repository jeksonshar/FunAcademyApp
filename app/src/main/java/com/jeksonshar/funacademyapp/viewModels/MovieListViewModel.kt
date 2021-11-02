package com.jeksonshar.funacademyapp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.jeksonshar.funacademyapp.db.room.SaveToRoom
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.db.room.Converters
import com.jeksonshar.funacademyapp.db.room.MovieDataBase
import com.jeksonshar.funacademyapp.db.room.MovieWithActorsAndGenes
import com.jeksonshar.funacademyapp.network.loadMoviePopularList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieListViewModel(private val application: Application) : ViewModel() {

    val db: MovieDataBase by lazy {
        MovieDataBase.createMovieDB(application.applicationContext)
    }

    private val saveData = SaveToRoom(db)
    val showUpdateProgress = MutableLiveData<Boolean>()
    val showToastNoMoviesToLoad = MutableLiveData(false)

    init {
        viewModelScope.launch {
            try {
                val apiMovies = loadMoviePopularList(1)
                if (!apiMovies.isNullOrEmpty()) {
                    saveData.saveMoviesToRoom(apiMovies)
                }
            } catch (e: Exception) {
                Log.d("Смотри - ", "EXCEPTION  ListViewModel: нет данных от API")
            }
        }
    }

    override fun onCleared() {
        db.close()
        super.onCleared()
    }

    private suspend fun getMoviesByPopularFromRoom(): List<Movie> {
        val movies: MutableList<Movie> = ArrayList()

        val movieEntities = db.moviesDao().getAllMoviesByPopular()
        val genreEntities = db.moviesDao().getGenresByMovie()
        val actorEntities = db.moviesDao().getActorsByMovie()

        for (movieEntity in movieEntities) {
            val movieWithActorsAndGenes = MovieWithActorsAndGenes(
                movieEntity,
                genreEntities,
                actorEntities
            )
            movies.add(Converters.convertToMovie(movieWithActorsAndGenes))
        }
        return movies
    }

    fun observeAllMoviesByPopular(): LiveData<List<Movie>> {
        return db.moviesDao().observeAllMoviesByPopular().map {
            getMoviesByPopularFromRoom()
        }.asLiveData()
    }

    suspend fun loadNewPage() {
        showUpdateProgress.postValue(true)
//        try {
            val countOfLoadedMovies = db.moviesDao().getAllMoviesByPopular().size
            val nextPage = (countOfLoadedMovies / 20) + 1
            if (nextPage < 10) {
                val apiMovies = loadMoviePopularList(nextPage)
                if (!apiMovies.isNullOrEmpty()) {
                    saveData.saveMoviesToRoom(apiMovies)
                }
            } else {
                showToastNoMoviesToLoad.postValue(true)
            }
            showUpdateProgress.postValue(false)
//        } catch (e: Exception) {
//            Log.d("Смотри - ", "EXCEPTION  ListViewModel: нет данных от API")
//        }
    }
}