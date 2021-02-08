package com.jeksonshar.funacademyapp.viewModels

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
                _moviesLiveData.value = deffer.await()
            } catch (e: IOException) {
                Log.d("Смотри - ", "AndroidRuntime: FATAL EXCEPTION")
            }
        }

        viewModelScope.launch {
            val deffer = viewModelScope.async(Dispatchers.IO) {
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
            val genresEntity = db.moviesDao().getGenresByMovie(movieEntity.id)
            val actorsEntity = db.moviesDao().getActorsByMovie(movieEntity.id)
            val movieWithActorsAndGenes = MovieWithActorsAndGenes(movieEntity, genresEntity, actorsEntity)

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
//            for (genre in movie.genres) {
//                val genreEntity = GenreEntity(id = genre.id, name = genre.name, movieId = movie.id)
//                genreEntities.add(genreEntity)
//            }
            genreEntities.addAll(Converters.convertToGenreEntity(movie))
            actorEntities.addAll(Converters.convertToActorEntity(movie))
        }
        for (movie in movies) {
            if (!db.moviesDao().getAllMoviesByPopular().isNullOrEmpty()) {
                db.moviesDao().deleteAllMovies()
            } else if (!db.moviesDao().getGenresByMovie(movie.id).isNullOrEmpty()) {
                db.moviesDao().deleteGenres()
            } else if (!db.moviesDao().getActorsByMovie(movie.id).isNullOrEmpty()) {
                db.moviesDao().deleteActors()
            }
        }

        db.moviesDao().insertAllMovies(movieEntities) // тут отрабатывает
        db.moviesDao().insertGenres(genreEntities) // тут все падает
        db.moviesDao().insertActors(actorEntities)
    }
}