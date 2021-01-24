package com.jeksonshar.funacademyapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieListViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(/*application*/) as T
        } else {
            throw IllegalArgumentException("Unknown MovieListViewModel class")
        }
    }
}