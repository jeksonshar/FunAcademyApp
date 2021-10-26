package com.jeksonshar.funacademyapp.db.room

import androidx.room.*
import com.jeksonshar.funacademyapp.db.room.models.ActorEntity
import com.jeksonshar.funacademyapp.db.room.models.GenreEntity
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    suspend fun getAllMoviesByPopular(): List<MovieEntity>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun observeAllMoviesByPopular(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM genres")
    suspend fun getGenresByMovie(): List<GenreEntity>

    @Query("SELECT * FROM actors")
    suspend fun getActorsByMovie(): List<ActorEntity>

    @Query("SELECT * FROM movies WHERE id == :idMovie")
    suspend fun getMovieById(idMovie: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<MovieEntity>)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenres(genres: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertActors(actors: List<ActorEntity>)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Query("DELETE FROM genres")
    suspend fun deleteGenres()

    @Query("DELETE FROM actors")
    suspend fun deleteActors()
}