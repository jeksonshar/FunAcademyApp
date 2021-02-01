package com.jeksonshar.funacademyapp.db.room.models

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "actors",
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("movieId"),
        onDelete = CASCADE
    )],
    indices = [Index(value = ["movieId"])]
)
data class ActorEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    val name: String = "",
    val picture: String = "",
    val movieId: Int = 0
)
