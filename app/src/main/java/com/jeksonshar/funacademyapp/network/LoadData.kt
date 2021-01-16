package com.jeksonshar.funacademyapp.network

import com.jeksonshar.funacademyapp.data.Actor
import com.jeksonshar.funacademyapp.data.Genre
import com.jeksonshar.funacademyapp.data.Movie
import kotlinx.coroutines.*
import java.util.*

suspend fun loadGenre(): List<Genre> {
    val data = RetrofitModule.moviesApi.getGenres(Locale.getDefault().language.toString()).genres
    return data.map {
        Genre(
            id = it.id,
            name = it.name
        )
    }
}

suspend fun loadBaseUrlPlace(): String = withContext(Dispatchers.IO) {
    return@withContext RetrofitModule.moviesApi.getConfiguration().images.secureBaseUrl
}

suspend fun loadMovieRuntimePlace(id: Int): Int = withContext(Dispatchers.IO) {
    return@withContext RetrofitModule.moviesApi
        .getMovieDetails(id, Locale.getDefault().language.toString()).runtime
}

suspend fun loadActorsByMovie(id: Int): List<Actor> = withContext(Dispatchers.IO) {
    val baseUrl = loadBaseUrlPlace()
    val data =
        RetrofitModule.moviesApi.getActorsByMovie(id, Locale.getDefault().language.toString()).cast
    return@withContext data.map {
        Actor(
            id = it.id,
            name = it.name,
            picture = baseUrl + "w500" + it.profilePath
        )
    }
}

suspend fun loadMoviePopularList(): List<Movie> = withContext(Dispatchers.IO) {
    val data =
        RetrofitModule.moviesApi.getMoviesPopular(Locale.getDefault().language.toString()).movies
    val genresMap = loadGenre().associateBy { it.id }
    val baseUrl = loadBaseUrlPlace()

    return@withContext data.map {
        Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            poster = baseUrl + "w500" + it.posterPath,
            backdrop = baseUrl + "w500" + it.backdropPath,
            ratings = it.voteAverage,
            numberOfRatings = it.voteCount,
            minimumAge = if (it.adult) 16 else 13,
            runtime = loadMovieRuntimePlace(it.id),
            genres = it.genreIds.map { its ->
                genresMap[its] ?: throw IllegalArgumentException("Genre not found")
            },
            actors = emptyList()
        )
    }
}

suspend fun loadMovieDetails(id: Int): Movie = withContext(Dispatchers.IO) {
    val data = RetrofitModule.moviesApi
        .getMovieDetails(id, Locale.getDefault().language.toString())
    val baseUrl = loadBaseUrlPlace()

    return@withContext Movie(
        id = data.id,
        title = data.title,
        overview = data.overview,
        poster = baseUrl + "w500" + data.posterPath,
        backdrop = baseUrl + "w500" + data.backdropPath,
        ratings = data.voteAverage,
        numberOfRatings = data.voteCount,
        minimumAge = if (data.adult) 16 else 13,
        runtime = 100,
        genres = data.genres,
        actors = loadActorsByMovie(data.id)
    )
}