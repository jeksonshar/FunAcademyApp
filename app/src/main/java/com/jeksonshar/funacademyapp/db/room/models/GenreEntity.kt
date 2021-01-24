package com.jeksonshar.funacademyapp.db.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    val name: String = ""
)
