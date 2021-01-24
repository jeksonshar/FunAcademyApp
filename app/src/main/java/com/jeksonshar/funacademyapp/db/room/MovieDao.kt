package com.jeksonshar.funacademyapp.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieEntity>

    @Insert
    fun insertAll(movies: List<MovieEntity>)
}