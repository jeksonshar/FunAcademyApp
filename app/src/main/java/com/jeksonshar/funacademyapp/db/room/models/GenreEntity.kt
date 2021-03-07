package com.jeksonshar.funacademyapp.db.room.models

import androidx.room.*

@Entity(
    tableName = "genres"
)
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    val name: String = ""
)
