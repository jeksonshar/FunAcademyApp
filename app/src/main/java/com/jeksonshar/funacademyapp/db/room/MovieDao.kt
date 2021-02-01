package com.jeksonshar.funacademyapp.db.room

import androidx.room.*
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id == :id")
    fun getMovieById(id: Int): MovieEntity

    @Insert
    fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM movies" )
    fun deleteAll()
}