package com.jeksonshar.funacademyapp.network

import com.jeksonshar.funacademyapp.network.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {

    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationModel

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("language") systemLang: String
    ): GenreListModel

    @GET("movie/popular")
    suspend fun getMoviesPopular(
        @Query("language") systemLang: String
    ): MovieListModel

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") systemLang: String
    ): MovieDetailsModel

    @GET("movie/{movie_id}/credits")
    suspend fun getActorsByMovie(
        @Path("movie_id") movieId: Int,
        @Query("language") systemLang: String
    ): MovieCrewCast
}