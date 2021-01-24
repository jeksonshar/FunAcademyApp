package com.jeksonshar.funacademyapp.db.room.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeksonshar.funacademyapp.data.Actor
import com.jeksonshar.funacademyapp.data.Genre

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_movie")
    val id: Int = 0,

    val title: String = "",
    val overview: String = "",
    val poster: String = "",
    val backdrop: String = "",
    val ratings: Float = 0F,
    val numberOfRatings: Int = 0,
    val minimumAge: Int = 0,
    val runtime: Int = 0,

    @Embedded
    val genres: List<GenreEntity> = emptyList(),
    @Embedded
    val actors: List<ActorEntity> = emptyList(),
    var isFavorite: Boolean = false
)