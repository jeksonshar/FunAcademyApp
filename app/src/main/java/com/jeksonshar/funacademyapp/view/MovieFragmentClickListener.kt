package com.jeksonshar.funacademyapp.view

import com.jeksonshar.funacademyapp.data.Movie

interface MovieFragmentClickListener {
    fun addMovieDetailFragment(movie: Movie)
    fun changeFavoriteValue(movie: Movie)
}