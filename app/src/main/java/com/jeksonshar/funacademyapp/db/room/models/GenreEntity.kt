package com.jeksonshar.funacademyapp.db.room.models

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "genres"/*,
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("movieId"),
        onDelete = CASCADE
    )],
    indices = [Index(value = ["movieId"])]*/
)
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    val name: String = "",
    val movieId: Int = 0
)
