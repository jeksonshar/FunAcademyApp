package com.jeksonshar.funacademyapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.network.loadMovieDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val idMovie: Int
) : ViewModel() {

    private val _currentMovieLiveData = MutableLiveData<Movie>()
    val currentMovieLiveData: LiveData<Movie> = _currentMovieLiveData

    init {
        viewModelScope.launch {
            val deffer = viewModelScope.async {
                loadMovieDetails(idMovie)
            }
            _currentMovieLiveData.value = deffer.await()
        }
    }
}