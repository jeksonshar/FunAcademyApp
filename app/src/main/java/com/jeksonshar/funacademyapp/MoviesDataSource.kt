package com.jeksonshar.funacademyapp

class MoviesDataSource {

    fun getMovies(): List<MovieDataModel> {
        return listOf(
            MovieDataModel(
                R.string.name_movie.toString(),
                R.drawable.background,
                "17.5+",
                R.string.tag_movie.toString(),
                3,
                "152 REVIEWS",
                R.string.description_detail_movie.toString()
            )
        )
    }
}