package com.jeksonshar.funacademyapp.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.db.room.MovieDataBase
import com.jeksonshar.funacademyapp.db.room.SaveToRoom
import com.jeksonshar.funacademyapp.network.loadMoviePopularList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieUpdateWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    val db = MovieDataBase.createMovieDB(applicationContext)
    private val saveData = SaveToRoom(db)

    override fun doWork(): Result {
        val scope = CoroutineScope(Dispatchers.IO)
        return try {
            scope.launch {
                val movies: MutableList<Movie> = ArrayList()
                val apiMovies: List<Movie> = emptyList()
                val sizeDB = db.moviesDao().getAllMoviesByPopular().size
                for (page in 1..(sizeDB / 20)) {
                    apiMovies.plus(loadMoviePopularList(page))
                }

                if (!apiMovies.isNullOrEmpty()) {
                    movies.addAll(apiMovies)
                    saveData.saveMoviesToRoom(movies)
                    Log.d("Смотри - ", "обновление базы выполнено")
                }
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}