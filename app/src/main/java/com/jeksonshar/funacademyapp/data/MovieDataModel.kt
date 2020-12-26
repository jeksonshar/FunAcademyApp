package com.jeksonshar.funacademyapp.data

import java.util.*

data class MovieDataModel(
    val nameMovie: String,
    val avatarMovie: Int,
    val avatarMovieForDetail: Int,
    val ageCategoryMovie: String,
    val tagMovie: String,
    val ratingMovie: Int,
    val numberReviewsMovie: String,
    val descriptionMovies: String,
    val durationMovie: String,
    var isFavorite: Boolean,
    val actorsMovie: List<MovieActorModel>,
    val idMovie: UUID = UUID.randomUUID()
)
