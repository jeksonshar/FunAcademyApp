package com.jeksonshar.funacademyapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeksonshar.funacademyapp.db.room.MovieDataBase

class MovieListViewModelFactory(val db: MovieDataBase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(db) as T
        } else {
            throw IllegalArgumentException("Unknown MovieListViewModel class")
        }
    }
}