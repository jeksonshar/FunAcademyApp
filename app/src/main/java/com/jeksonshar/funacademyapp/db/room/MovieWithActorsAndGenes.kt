package com.jeksonshar.funacademyapp.db.room

import androidx.room.Embedded
import com.jeksonshar.funacademyapp.db.room.models.ActorEntity
import com.jeksonshar.funacademyapp.db.room.models.GenreEntity
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity

data class MovieWithActorsAndGenes(
    @Embedded
    val movieEntity: MovieEntity,

    @Embedded
    val genres: List<GenreEntity>,

    @Embedded
    val actors: List<ActorEntity>
)
