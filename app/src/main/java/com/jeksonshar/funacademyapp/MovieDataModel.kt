package com.jeksonshar.funacademyapp

import java.util.*

data class MovieDataModel(
    val nameMovie: String,
    val avatarMovie: Int,
    val ageCategoryMovie: String,
    val tagMovie: String,
    val ratingMovie: Int,
    val numberReviewsMovie: String,
    val descriptionMovies: String,
//    val movieActor1: ImageView,
//    val movieActor2: ImageView,
//    val movieActor3: ImageView,
//    val movieActor4: ImageView,
//    val movieActorName1: String,
//    val movieActorName2: String,
//    val movieActorName3: String,
//    val movieActorName4: String,
    val durationMovie: String,
    val idMovie: UUID = UUID.randomUUID()
)
