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
    fun convertToMovieEntity(movie: Movie): MovieEntity {
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
    fun convertToGenreEntity(movie: Movie): List<GenreEntity> {
        val genres: MutableList<GenreEntity> = ArrayList()
        for (genre in movie.genres) {
            genres.add(
                GenreEntity(
                    id = genre.id,
                    name = genre.name,
                    movieId = movie.id
                )
            )
        }
        return genres
    }

    @TypeConverter
    fun convertToActorEntity(movie: Movie): List<ActorEntity> {
        val actors: MutableList<ActorEntity> = ArrayList()
        for (actor in movie.actors) {
            actors.add(
                ActorEntity(
                    id = actor.id,
                    name = actor.name,
                    picture = actor.picture,
                    movieId = movie.id
                )
            )
        }
        return actors
    }

    private fun convertToGenre(genreEntity: GenreEntity): Genre {
        return Genre(
            name = genreEntity.name,
            id = genreEntity.id
        )
    }

    private fun convertToListGenre(genres: List<GenreEntity>): List<Genre> {
        val genresList: MutableList<Genre> = ArrayList()
        for (genre in genres) {
            genresList.add(convertToGenre(genre))
        }
        return genresList
    }

    private fun convertToActor(actorEntity: ActorEntity): Actor {
        return Actor(
            id = actorEntity.id,
            name = actorEntity.name,
            picture = actorEntity.picture
        )
    }

    private fun convertToListActor(actors: List<ActorEntity>): List<Actor> {
        val actorsList: MutableList<Actor> = ArrayList()
        for (actor in actors) {
            actorsList.add(convertToActor(actor))
        }
        return actorsList
    }

    @TypeConverter
    fun convertToMovie(movieWithActorsAndGenes: MovieWithActorsAndGenes): Movie {
        return Movie(
            id = movieWithActorsAndGenes.movieEntity.id,
            title = movieWithActorsAndGenes.movieEntity.title,
            overview = movieWithActorsAndGenes.movieEntity.overview,
            poster = movieWithActorsAndGenes.movieEntity.poster,
            backdrop = movieWithActorsAndGenes.movieEntity.backdrop,
            ratings = movieWithActorsAndGenes.movieEntity.ratings,
            numberOfRatings = movieWithActorsAndGenes.movieEntity.numberOfRatings,
            minimumAge = movieWithActorsAndGenes.movieEntity.minimumAge,
            runtime = movieWithActorsAndGenes.movieEntity.runtime,
            genres = convertToListGenre(movieWithActorsAndGenes.genres),
            actors = convertToListActor(movieWithActorsAndGenes.actors),
            popularity = movieWithActorsAndGenes.movieEntity.popularity
        )
    }
}