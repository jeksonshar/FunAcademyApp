package com.jeksonshar.funacademyapp.db.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,

    val title: String = "",
    val overview: String = "",
    val popularity: Float = 0F,
    val poster: String = "",
    val backdrop: String = "",
    val ratings: Float = 0F,
    val numberOfRatings: Int = 0,
    val minimumAge: Int = 0,
    val runtime: Int = 0,
    val genres: String = "",
    val actors: String = ""
)