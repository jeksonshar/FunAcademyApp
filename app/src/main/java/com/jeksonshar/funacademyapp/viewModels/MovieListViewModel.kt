package com.jeksonshar.funacademyapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.db.room.Converters
import com.jeksonshar.funacademyapp.db.room.MovieDataBase
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity
import com.jeksonshar.funacademyapp.network.loadMoviePopularList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.IOException

class MovieListViewModel(val db: MovieDataBase) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData

    init {
        viewModelScope.launch {
            val deffer = viewModelScope.async(Dispatchers.IO) {
                var movies = emptyList<Movie>()
                val cashMovies = getMoviesFromRoom()
                if (!cashMovies.isNullOrEmpty()) {
                    movies = cashMovies
                }
                movies
            }
            try {
                val deffer = deffer.await()
                _moviesLiveData.value = deffer
            } catch (e: IOException) {
                Log.d("Смотри - ", "AndroidRuntime: FATAL EXCEPTION")
            }
        }

        viewModelScope.launch {
            val deffer = viewModelScope.async {
                var movies = emptyList<Movie>()
                val apiMovies = loadMoviePopularList()
                if (!apiMovies.isNullOrEmpty()) {
                    movies = apiMovies
                    saveMoviesToRoom(movies)
                }
                movies
            }
            try {
                val resDefer = deffer.await()
                if (!resDefer.isNullOrEmpty()) {
                    _moviesLiveData.value = resDefer
                }
            } catch (e: IOException) {
                Log.d("Смотри - ", "AndroidRuntime: FATAL EXCEPTION")
            }
        }
    }

    private suspend fun getMoviesFromRoom(): List<Movie> {
        val movies: MutableList<Movie> = ArrayList()
        val movieEntities = db.moviesDao().getAllMoviesByPopular()
        for (movieEntity in movieEntities) {
            movies.add(Converters.convertToMovie(movieEntity))
        }
        return movies
    }

    private suspend fun saveMoviesToRoom(movies: List<Movie>) {
        val movieEntities: MutableList<MovieEntity> = ArrayList()
        for (movie in movies) {
            movieEntities.add(Converters.convertToEntity(movie))
        }
        if (!db.moviesDao().getAllMoviesByPopular().isNullOrEmpty()) {
            db.moviesDao().deleteAll()
        }
        db.moviesDao().insertAll(movieEntities)
    }
}