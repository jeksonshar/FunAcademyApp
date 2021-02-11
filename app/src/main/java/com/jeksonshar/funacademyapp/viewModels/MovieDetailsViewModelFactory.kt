package com.jeksonshar.funacademyapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeksonshar.funacademyapp.db.room.MovieDataBase

class MovieDetailsViewModelFactory(
    private val idMovie: Int,
    val db: MovieDataBase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(idMovie, db) as T
        } else {
            throw IllegalArgumentException("Unknown MovieDetailsViewModel class")
        }
    }

}