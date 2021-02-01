package com.jeksonshar.funacademyapp.db.room

import androidx.room.TypeConverter
import com.jeksonshar.funacademyapp.data.Actor
import com.jeksonshar.funacademyapp.data.Genre
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.db.room.models.ActorEntity
import com.jeksonshar.funacademyapp.db.room.models.GenreEntity
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity

object Converters {

    @TypeConverter
    fun convertToEntity(movie: Movie): MovieEntity {
       return MovieEntity(
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            poster = movie.poster,
            backdrop = movie.backdrop,
            ratings = movie.ratings,
            numberOfRatings = movie.numberOfRatings,
            minimumAge = movie.minimumAge,
            runtime = movie.runtime,
            genres = movie.genres as List<GenreEntity>,
            actors = movie.actors as List<ActorEntity>,
            isFavorite = movie.isFavorite
        )
    }

    @TypeConverter
    fun convertToMovie(movieEntity: MovieEntity): Movie {
        return Movie(
            id =movieEntity.id,
            title = movieEntity.title,
            overview = movieEntity.overview,
            poster = movieEntity.poster,
            backdrop = movieEntity.backdrop,
            ratings = movieEntity.ratings,
            numberOfRatings = movieEntity.numberOfRatings,
            minimumAge = movieEntity.minimumAge,
            runtime = movieEntity.runtime,
            genres = movieEntity.genres as List<Genre>,
            actors = movieEntity.actors as List<Actor>,
            isFavorite = movieEntity.isFavorite
        )
    }
}