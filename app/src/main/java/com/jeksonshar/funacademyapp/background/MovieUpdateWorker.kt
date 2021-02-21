package com.jeksonshar.funacademyapp.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.db.room.Converters
import com.jeksonshar.funacademyapp.db.room.MovieDataBase
import com.jeksonshar.funacademyapp.db.room.models.ActorEntity
import com.jeksonshar.funacademyapp.db.room.models.GenreEntity
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity
import com.jeksonshar.funacademyapp.network.loadMoviePopularList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieUpdateWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    val db = MovieDataBase.createMovieDB(applicationContext)

    override fun doWork(): Result {
        val scope = CoroutineScope(Dispatchers.IO)
        return try {
            scope.launch {
                val movies: MutableList<Movie> = ArrayList()
                val apiMovies = loadMoviePopularList()
                if (!apiMovies.isNullOrEmpty()) {
                    movies.addAll(apiMovies)
                    saveMoviesToRoom(movies)
                    Log.d("Смотри - ", "обновление базы выполнено")
                }
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    //этот код дублируется, нужно исправить
    private suspend fun saveMoviesToRoom(movies: List<Movie>) {

        val movieEntities: MutableList<MovieEntity> = ArrayList()
        val genreEntities: MutableList<GenreEntity> = ArrayList()
        val actorEntities: MutableList<ActorEntity> = ArrayList()
        for (movie in movies) {
            movieEntities.add(Converters.convertToMovieEntity(movie))
            genreEntities.addAll(Converters.convertToGenreEntity(movie))
            actorEntities.addAll(Converters.convertToActorEntity(movie))
        }

        if (!db.moviesDao().getAllMoviesByPopular().isNullOrEmpty()) {
            db.moviesDao().deleteAllMovies()
        }

        if (!db.moviesDao().getGenresByMovie().isNullOrEmpty()) {
            db.moviesDao().deleteGenres()
        }

        if (!db.moviesDao().getActorsByMovie().isNullOrEmpty()) {
            db.moviesDao().deleteActors()
        }

        db.moviesDao().insertAllMovies(movieEntities)
        db.moviesDao().insertGenres(genreEntities)
        db.moviesDao().insertActors(actorEntities)
    }
}