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
import com.jeksonshar.funacademyapp.network.loadMovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.IOException

class MovieDetailsViewModel(
    private val idMovie: Int,
    val db: MovieDataBase
) : ViewModel() {

    private val _currentMovieLiveData = MutableLiveData<Movie>()
    val currentMovieLiveData: LiveData<Movie> = _currentMovieLiveData

    init {
        viewModelScope.launch {
            val deffer = viewModelScope.async(Dispatchers.IO) {
                getMovieFromRoom(idMovie)
            }
            try {
                _currentMovieLiveData.value = deffer.await()
            } catch (e: IOException) {
                Log.d("Смотри - ", "AndroidRuntime: FATAL EXCEPTION DetailsViewModel")
            }
        }

        viewModelScope.launch {
            val deffer = viewModelScope.async(Dispatchers.IO) {
                val apiMovie = loadMovieDetails(idMovie)
                var updateMovie: Movie = apiMovie
                if (apiMovie.id == 0) {
                    updateMovie = getMovieFromRoom(idMovie)
                }
                updateMovie
            }
            try {
                _currentMovieLiveData.value = deffer.await()
            } catch (e: IOException) {
                Log.d("Смотри - ", " EXCEPTION DetailsViewModel: нет данных от API")
            }
        }
    }

    private suspend fun getMovieFromRoom(idMovie: Int): Movie {
        val movieEntity = db.moviesDao().getMovieById(idMovie)
        val genreEntities = db.moviesDao().getGenresByMovie()
        val actorEntities = db.moviesDao().getActorsByMovie()
        val movieWithActorsAndGenes = MovieWithActorsAndGenes(
            movieEntity,
            genreEntities,
            actorEntities
        )
        return Converters.convertToMovie(movieWithActorsAndGenes)
    }
}