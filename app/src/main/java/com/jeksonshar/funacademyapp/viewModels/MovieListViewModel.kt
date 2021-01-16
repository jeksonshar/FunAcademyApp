package com.jeksonshar.funacademyapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.network.loadMoviePopularList
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData

    init {
        viewModelScope.launch {
            val deffer = viewModelScope.async {
                loadMoviePopularList()
            }
            _moviesLiveData.value = deffer.await()
        }
    }
}