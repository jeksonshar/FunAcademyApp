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
            popularity = movie.popularity,
            poster = movie.poster,
            backdrop = movie.backdrop,
            ratings = movie.ratings,
            numberOfRatings = movie.numberOfRatings,
            releaseDate = movie.releaseDate,
            countries = movie.countries,
            minimumAge = movie.minimumAge,
            runtime = movie.runtime,
            genres = convertGenresIdToString(movie),
            actors = convertActorsIdToString(movie)
        )
    }

    private fun convertGenresIdToString(movie: Movie): String {
        val genresId: MutableList<Int> = ArrayList()
        for (genre in movie.genres) {
            genresId.add(genre.id)
        }
        return (genresId as List<*>).joinToString(",")
    }

    private fun convertActorsIdToString(movie: Movie): String {
        val actorsId: MutableList<Int> = ArrayList()
        for (actor in movie.actors) {
            actorsId.add(actor.id)
        }
        return (actorsId as List<*>).joinToString(",")
    }

    @TypeConverter
    fun convertToGenreEntity(movie: Movie): List<GenreEntity> {
        val genres: MutableList<GenreEntity> = ArrayList()
        for (genre in movie.genres) {
            genres.add(
                GenreEntity(
                    id = genre.id,
                    name = genre.name
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
                    picture = actor.picture
                )
            )
        }
        return actors
    }

    @TypeConverter
    fun convertToMovie(movieWithActorsAndGenes: MovieWithActorsAndGenes): Movie {
        val genresMap = movieWithActorsAndGenes.genres.associateBy { it.id }
        val actorsMap = movieWithActorsAndGenes.actors.associateBy { it.id }

        return with(movieWithActorsAndGenes.movieEntity) {
            Movie(
                id = id,
                title = title,
                overview = overview,
                popularity = popularity,
                poster = poster,
                backdrop = backdrop,
                ratings = ratings,
                numberOfRatings = numberOfRatings,
                releaseDate = releaseDate,
                countries = countries,
                minimumAge = minimumAge,
                runtime = runtime,
                genres = convertToListGenre(genres).map {
                    convertToGenre(genresMap[it] ?: throw IllegalArgumentException("Genre not found"))
                },
                actors = convertToListActor(actors).map {
                    convertToActor(actorsMap[it] ?: throw IllegalArgumentException("Actor not found"))
                }
            )
        }
    }

    private fun convertToListGenre(genreString: String): List<Int> {
        val genreListString = genreString.split(",")
        val genreListInt: MutableList<Int> = ArrayList()
        for (genre in genreListString) {
            genreListInt.add(genre.toInt())
        }
        return genreListInt
    }

    private fun convertToGenre(genreEntity: GenreEntity): Genre {
        return Genre(
            name = genreEntity.name,
            id = genreEntity.id
        )
    }

    private fun convertToListActor(actorString: String): List<Int> {
        val actorListString = actorString.split(",")
        val actorsListInt: MutableList<Int> = ArrayList()
        for (actor in actorListString) {
            actorsListInt.add(actor.toInt())
        }
        return actorsListInt
    }

    private fun convertToActor(actorEntity: ActorEntity): Actor {
        return Actor(
            id = actorEntity.id,
            name = actorEntity.name,
            picture = actorEntity.picture
        )
    }
}