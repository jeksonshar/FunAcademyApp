package com.jeksonshar.funacademyapp.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeksonshar.funacademyapp.db.room.models.ActorEntity
import com.jeksonshar.funacademyapp.db.room.models.GenreEntity
import com.jeksonshar.funacademyapp.db.room.models.MovieEntity

@Database(
    entities = [MovieEntity::class, GenreEntity::class, ActorEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun moviesDao(): MovieDao

    companion object {
        private const val DATABASE_NAME = "Movies.db"

        fun createMovieDB(context: Context): MovieDataBase {
            return Room.databaseBuilder(
                context,
                MovieDataBase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}