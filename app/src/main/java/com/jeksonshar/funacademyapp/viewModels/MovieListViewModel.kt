package com.jeksonshar.funacademyapp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.db.room.Converters
import com.jeksonshar.funacademyapp.db.room.MovieDataBase
import com.jeksonshar.funacademyapp.db.room.MovieWithActorsAndGenes
import com.jeksonshar.funacademyapp.db.room.models.ActorEntity
import com.jeksonshar.funacademyapp.db.room.models.GenreEntity
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity
import com.jeksonshar.funacademyapp.network.loadMoviePopularList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.IOException

class MovieListViewModel(private val application: Application) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData

    val db: MovieDataBase by lazy {
        MovieDataBase.createMovieDB(application.applicationContext)
    }

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
                    saveMoviesToRoom(apiMovies)
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

    private suspend fun saveMoviesToRoom(movies: List<Movie>) {
        val movieEntities: MutableList<MovieEntity> = ArrayList()
        val genreEntities: MutableList<GenreEntity> = ArrayList()
        val actorEntities: MutableList<ActorEntity> = ArrayList()
        for (movie in movies) {
            movieEntities.add(Converters.convertToMovieEntity(movie))
            genreEntities.addAll(Converters.convertToGenreEntity(movie))
            actorEntities.addAll(Converters.convertToActorEntity(movie))
        }

        if (!db.moviesDao().getAllMoviesByPopular().isNullOrEmpty()) {
            db.moviesDao().deleteAllMovies()
        }

        if (!db.moviesDao().getGenresByMovie().isNullOrEmpty()) {
            db.moviesDao().deleteGenres()
        }

        if (!db.moviesDao().getActorsByMovie().isNullOrEmpty()) {
            db.moviesDao().deleteActors()
        }

        db.moviesDao().insertAllMovies(movieEntities)
        db.moviesDao().insertGenres(genreEntities)
        db.moviesDao().insertActors(actorEntities)
    }
}