package com.jeksonshar.funacademyapp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeksonshar.funacademyapp.db.room.SaveToRoom
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.db.room.Converters
import com.jeksonshar.funacademyapp.db.room.MovieDataBase
import com.jeksonshar.funacademyapp.db.room.MovieWithActorsAndGenes
import com.jeksonshar.funacademyapp.network.loadMoviePopularList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.IOException

class MovieListViewModel(private val application: Application) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData

    private val _res = MutableLiveData<List<Movie>>()
    val res: LiveData<List<Movie>> = _res

    val db: MovieDataBase by lazy {
        MovieDataBase.createMovieDB(application.applicationContext)
    }

    private val saveData = SaveToRoom(db)

    init {
        viewModelScope.launch {
            val deffer = viewModelScope.async(Dispatchers.IO) {
                var movies = emptyList<Movie>()
                val cacheMovies = getMoviesByPopularFromRoom()
                if (!cacheMovies.isNullOrEmpty()) {
                    movies = cacheMovies
                }
                movies
            }
            try {
                _moviesLiveData.value = deffer.await()
            } catch (e: IOException) {
                Log.d("Смотри - ", "AndroidRuntime: FATAL EXCEPTION ListViewModel")
            }
        }

        viewModelScope.launch {
            val deffer = viewModelScope.async(Dispatchers.IO) {
                val apiMovies = loadMoviePopularList()
                if (!apiMovies.isNullOrEmpty()) {
                    saveData.saveMoviesToRoom(apiMovies)
                }
                getMoviesByPopularFromRoom()
            }
            try {
                val resDefer = deffer.await()
                if (!resDefer.isNullOrEmpty()) {
                    _moviesLiveData.value = resDefer
                }
            } catch (e: IOException) {
                Log.d("Смотри - ", "EXCEPTION  ListViewModel: нет данных от API")
            }
        }
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

    // ??????????
    fun observeMoviesUpdates(): LiveData<List<Movie>> {
        viewModelScope.launch {
            _res.value = getMoviesByPopularFromRoom()
        }
        return res
    }
}