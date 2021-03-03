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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieListViewModel(private val application: Application) : ViewModel() {

    val db: MovieDataBase by lazy {
        MovieDataBase.createMovieDB(application.applicationContext)
    }

    private val saveData = SaveToRoom(db)

    init {
        viewModelScope.launch {
            var apiMovies = emptyList<Movie>()
            try {
                apiMovies = loadMoviePopularList()
            } catch (e: Exception) {
                Log.d("Смотри - ", "EXCEPTION  ListViewModel: нет данных от API")
            } finally {
                if (!apiMovies.isNullOrEmpty()) {
                    saveData.saveMoviesToRoom(apiMovies)
                }
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
}