package com.jeksonshar.funacademyapp.db

import android.content.Context
import android.content.SharedPreferences
import com.jeksonshar.funacademyapp.data.Movie

class FavoriteSharedPreferences(context: Context) {

    private val sPreferences: SharedPreferences =
        context.getSharedPreferences("Save", Context.MODE_PRIVATE)

    fun saveFavoriteMovie(movie: Movie) {
        sPreferences.edit()?.putBoolean("${movie.id}", movie.isFavorite)?.apply()
        listFavorite = listFavorite.plus(sPreferences)
    }

    fun deleteFavoriteMovie(movie: Movie) {
        sPreferences.edit()?.remove("${movie.id}")?.apply()
        listFavorite = listFavorite.plus(sPreferences)
    }

    fun update() {
        sPreferences.edit()?.apply()
        listFavorite = listFavorite.plus(sPreferences)
    }

    companion object {
        var listFavorite: List<SharedPreferences> = emptyList()
    }

}