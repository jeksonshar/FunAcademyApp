package com.jeksonshar.funacademyapp.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.data.loadMovies
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val application: Application,
    private val idMovie: Int
    ): ViewModel() {

    private val _currentMovieLiveData = MutableLiveData<Movie>()
    val currentMovieLiveData: LiveData<Movie> = _currentMovieLiveData

    init {
        viewModelScope.launch {
            val deffer = viewModelScope.async { loadMovies(application.applicationContext) }
            val movieList: List<Movie> = deffer.await()

            for (movie: Movie in movieList) {
                if (movie.id == idMovie) {
                    _currentMovieLiveData.value = movie
                }
            }
        }
    }
}