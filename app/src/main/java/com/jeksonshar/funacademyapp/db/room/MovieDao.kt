package com.jeksonshar.funacademyapp.db.room

import androidx.room.*
import com.jeksonshar.funacademyapp.db.room.models.ActorEntity
import com.jeksonshar.funacademyapp.db.room.models.GenreEntity
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    suspend fun getAllMoviesByPopular(): List<MovieEntity>

    @Query("SELECT * FROM genres WHERE movieId == :id")
    fun getGenresByMovie(id: Int): List<GenreEntity>

    @Query("SELECT * FROM actors WHERE movieId == :id")
    fun getActorsByMovie(id: Int): List<ActorEntity>

    @Insert
    suspend fun insertAllMovies(movies: List<MovieEntity>)

    @Insert
    suspend fun insertGenres(genres: List<GenreEntity>)

    @Insert
    suspend fun insertActors(actors: List<ActorEntity>)

    @Query("DELETE FROM movies" )
    suspend fun deleteAllMovies()

    @Query("DELETE FROM genres")
    suspend fun deleteGenres()

    @Query("DELETE FROM actors")
    suspend fun deleteActors()
}