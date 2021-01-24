package com.jeksonshar.funacademyapp.db

import android.content.Context

object RepositoryProvider {
    private var instanceFavoriteMovies: FavoriteSharedPreferences? = null

    fun getInstanceFavoriteMovies(context: Context): FavoriteSharedPreferences? {
        if (instanceFavoriteMovies == null) {
            instanceFavoriteMovies = FavoriteSharedPreferences(context)
        }
        return instanceFavoriteMovies
    }
}