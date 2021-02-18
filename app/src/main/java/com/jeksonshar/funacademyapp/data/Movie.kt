package com.jeksonshar.funacademyapp.data

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val popularity: Float,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Int,
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>,
    var isFavorite: Boolean = false
)