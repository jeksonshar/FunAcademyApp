package com.jeksonshar.funacademyapp.db.room

import androidx.room.*
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    suspend fun getAllMoviesByPopular(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id == :id")
    fun getMovieById(id: Int): MovieEntity

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Insert
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM movies" )
    suspend fun deleteAll()
}