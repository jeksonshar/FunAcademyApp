package com.jeksonshar.funacademyapp.db.room

import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.db.room.models.ActorEntity
import com.jeksonshar.funacademyapp.db.room.models.GenreEntity
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity

class SaveToRoom(val db: MovieDataBase) {

    suspend fun saveMoviesToRoom(movies: List<Movie>) {
        val movieEntities: MutableList<MovieEntity> = ArrayList()
        val genreEntities: MutableList<GenreEntity> = ArrayList()
        val actorEntities: MutableList<ActorEntity> = ArrayList()

        for (movie in movies) {
//            movieEntities.add(Converters.convertToMovieEntity(movie)).id) == null) {
            db.moviesDao().insertMovie(Converters.convertToMovieEntity(movie))
            genreEntities.addAll(Converters.convertToGenreEntity(movie))
            actorEntities.addAll(Converters.convertToActorEntity(movie))
        }

//        if (!db.moviesDao().getAllMoviesByPopular().isNullOrEmpty()) {
//            db.moviesDao().deleteAllMovies()
//        }
//
//        if (!db.moviesDao().getGenresByMovie().isNullOrEmpty()) {
//            db.moviesDao().deleteGenres()
//        }
//
//        if (!db.moviesDao().getActorsByMovie().isNullOrEmpty()) {
//            db.moviesDao().deleteActors()
//        }

//        db.moviesDao().insertAllMovies(movieEntities)
        db.moviesDao().insertGenres(genreEntities)
        db.moviesDao().insertActors(actorEntities)
    }
}