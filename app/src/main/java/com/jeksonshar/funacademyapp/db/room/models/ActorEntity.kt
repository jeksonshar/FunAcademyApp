package com.jeksonshar.funacademyapp.db.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actors")
data class ActorEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    val name: String = "",
    val picture: String = ""
)
