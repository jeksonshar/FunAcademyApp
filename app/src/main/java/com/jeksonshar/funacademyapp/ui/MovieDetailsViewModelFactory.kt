package com.jeksonshar.funacademyapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieDetailsViewModelFactory(
    private val application: Application,
    private val idMovie: Int
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(application, idMovie) as T
        } else {
            throw IllegalArgumentException("Unknown MovieDetailsViewModel class")
        }
    }

}