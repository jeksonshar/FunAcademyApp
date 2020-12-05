package com.jeksonshar.funacademyapp

import android.provider.Settings.Global.getString
import android.view.View

class MoviesDataSource {

    companion object {
        fun getMovies(view: View): List<MovieDataModel> {
            return listOf(
                MovieDataModel(
                    view.context.getString(R.string.name_movie),
                    R.drawable.movie_list_image1,
                    "17+",
                    view.context.getString(R.string.tag_movie),
                    1,
                    "562 REVIEWS",
                    R.string.description_detail_movie.toString(),
                    "135 MIN"
                ),
                MovieDataModel(
                    view.context.getString(R.string.name_movie),
                    R.drawable.movie_list_image1,
                    "13+",
                    view.context.getString(R.string.tag_movie),
                    2,
                    "135 REVIEWS",
                    R.string.description_detail_movie.toString(),
                    "135 MIN"
                ),
                MovieDataModel(
                    view.context.getString(R.string.name_movie),
                    R.drawable.movie_list_image1,
                    "21+",
                    view.context.getString(R.string.tag_movie),
                    3,
                    "152 REVIEWS",
                    R.string.description_detail_movie.toString(),
                    "135 MIN"
                ),
                MovieDataModel(
                    view.context.getString(R.string.name_movie),
                    R.drawable.movie_list_image1,
                    "21+",
                    view.context.getString(R.string.tag_movie),
                    4,
                    "152 REVIEWS",
                    R.string.description_detail_movie.toString(),
                    "135 MIN"
                ),
                MovieDataModel(
                    view.context.getString(R.string.name_movie),
                    R.drawable.movie_list_image1,
                    "21+",
                    view.context.getString(R.string.tag_movie),
                    5,
                    "152 REVIEWS",
                    R.string.description_detail_movie.toString(),
                    "135 MIN"
                ),
                MovieDataModel(
                    view.context.getString(R.string.name_movie),
                    R.drawable.movie_list_image1,
                    "21+",
                    view.context.getString(R.string.tag_movie),
                    4,
                    "152 REVIEWS",
                    R.string.description_detail_movie.toString(),
                    "135 MIN"
                ),
                MovieDataModel(
                    view.context.getString(R.string.name_movie),
                    R.drawable.movie_list_image1,
                    "21+",
                    view.context.getString(R.string.tag_movie),
                    3,
                    "152 REVIEWS",
                    R.string.description_detail_movie.toString(),
                    "135 MIN"
                )
            )
        }
    }

}