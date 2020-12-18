package com.jeksonshar.funacademyapp.ui

import com.jeksonshar.funacademyapp.data.Movie

interface MovieFragmentClickListener {
    fun addMovieDetailFragment(movie: Movie)
    fun changeFavoriteValue(movie: Movie)
}