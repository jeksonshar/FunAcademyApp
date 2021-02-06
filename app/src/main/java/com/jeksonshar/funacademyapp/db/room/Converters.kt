package com.jeksonshar.funacademyapp.db.room

import androidx.room.TypeConverter
import com.jeksonshar.funacademyapp.data.Movie
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
            popularity = movie.popularity
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
            genres = emptyList(),
            actors = emptyList(),
            popularity = movieEntity.popularity
        )
    }
}