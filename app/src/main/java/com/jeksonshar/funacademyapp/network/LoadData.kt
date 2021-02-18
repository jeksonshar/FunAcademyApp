package com.jeksonshar.funacademyapp.network

import com.jeksonshar.funacademyapp.data.Actor
import com.jeksonshar.funacademyapp.data.Genre
import com.jeksonshar.funacademyapp.data.Movie
import kotlinx.coroutines.*
import java.util.*

suspend fun loadGenre(): List<Genre> {
    val data = RetrofitModule.moviesApi.getGenres(Locale.getDefault().language.toString()).genres
    return data?.map {
        Genre(
            id = it.id ?: 0,
            name = it.name ?: ""
        )
    }
        ?: emptyList()
}

suspend fun loadBaseUrlPlace(): String = withContext(Dispatchers.IO) {
    return@withContext RetrofitModule.moviesApi.getConfiguration().images.secureBaseUrl ?: ""
}

suspend fun loadMovieRuntimePlace(id: Int): Int = withContext(Dispatchers.IO) {
    return@withContext RetrofitModule.moviesApi
        .getMovieDetails(id, Locale.getDefault().language.toString()).runtime ?: 0
}

suspend fun loadActorsByMovie(id: Int): List<Actor> = withContext(Dispatchers.IO) {
    val baseUrl = loadBaseUrlPlace()
    val data =
        RetrofitModule.moviesApi.getActorsByMovie(id, Locale.getDefault().language.toString()).cast
    return@withContext data?.map {
        Actor(
            id = it.id ?: 0,
            name = it.name ?: "",
            picture = baseUrl + "w500" + it.profilePath
        )
    } ?: emptyList()
}

suspend fun loadMoviePopularList(): List<Movie> = withContext(Dispatchers.IO) {
    val data =
        RetrofitModule.moviesApi.getMoviesPopular(Locale.getDefault().language.toString()).movies
    val genresMap = loadGenre().associateBy { it.id }
    val baseUrl = loadBaseUrlPlace()

    return@withContext data?.map {
        Movie(
            id = it.id ?: 0,
            title = it.title ?: "",
            overview = it.overview ?: "",
            popularity = it.popularity ?: 0F,
            poster = baseUrl + "w500" + it.posterPath,
            backdrop = baseUrl + "w500" + it.backdropPath,
            ratings = it.voteAverage ?: 0F,
            numberOfRatings = it.voteCount ?: 0,
            minimumAge = if (it.adult == true) 16 else 13,
            runtime = loadMovieRuntimePlace(it.id ?: 0),
            genres = if (it.genreIds != null) {
                it.genreIds.map { its ->
                    genresMap[its] ?: throw IllegalArgumentException("Genre not found")
                }
            } else {
                emptyList()
            },
            actors = loadActorsByMovie(it.id ?: 0)
        )
    } ?: emptyList()
}

suspend fun loadMovieDetails(id: Int): Movie = withContext(Dispatchers.IO) {
    val data = RetrofitModule.moviesApi
        .getMovieDetails(id, Locale.getDefault().language.toString())
    val baseUrl = loadBaseUrlPlace()

    return@withContext Movie(
        id = data.id ?: 0,
        title = data.title ?: "",
        overview = data.overview ?: "",
        popularity = data.popularity ?: 0F,
        poster = baseUrl + "w500" + data.posterPath,
        backdrop = baseUrl + "w500" + data.backdropPath,
        ratings = data.voteAverage ?: 0F,
        numberOfRatings = data.voteCount ?: 0,
        minimumAge = if (data.adult == true) 16 else 13,
        runtime = 100,
        genres = data.genres ?: emptyList(),
        actors = loadActorsByMovie(data.id ?: 0)
    )
}