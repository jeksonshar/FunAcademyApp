package com.jeksonshar.funacademyapp.db.room

import androidx.room.Embedded
import androidx.room.Relation
import com.jeksonshar.funacademyapp.db.room.models.ActorEntity
import com.jeksonshar.funacademyapp.db.room.models.GenreEntity
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity

data class MovieWithActorsAndGenes(
    @Embedded
    val movieEntity: MovieEntity,

    @Relation(
        parentColumn = "id",
        entity = GenreEntity::class,
        entityColumn = "movieId"
    )
    val genres: List<GenreEntity>,

    @Relation(
        parentColumn = "id",
        entity = ActorEntity::class,
        entityColumn = "movieId"
    )
    val actors: List<ActorEntity>
)
