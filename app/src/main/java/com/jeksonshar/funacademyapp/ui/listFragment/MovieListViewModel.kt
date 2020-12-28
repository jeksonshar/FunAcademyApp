package com.jeksonshar.funacademyapp.ui.listFragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.data.loadMovies
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieListViewModel(private val application: Application) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData

    init {
        viewModelScope.launch {
            val deffer = viewModelScope.async { loadMovies(application.applicationContext) }
            _moviesLiveData.value = deffer.await()
        }
    }
}