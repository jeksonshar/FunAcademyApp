package com.jeksonshar.funacademyapp

import com.jeksonshar.funacademyapp.data.MovieDataModel

interface MovieFragmentClickListener {
    fun addMovieDetailFragment(movie: MovieDataModel)
    fun changeFavoriteValue(movie: MovieDataModel)
}