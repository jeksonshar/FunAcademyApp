package com.jeksonshar.funacademyapp.data

import android.view.View
import com.jeksonshar.funacademyapp.R
import java.util.*

class MoviesDataSource {

    companion object {
        fun getMovies(view: View): List<MovieDataModel> {
            return listOf(
                MovieDataModel(
                    view.context.getString(R.string.name_movie),
                    R.drawable.movie_list_image1,
                    R.drawable.orig,
                    "13+",
                    view.context.getString(R.string.tag_movie),
                    4,
                    "125 REVIEWS",
                    view.context.getString(R.string.description_detail_movie),
                    "135 MIN",
                    false,
                    listOf(
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        ),
                        MovieActorModel(
                            R.drawable.movie_4,
                            view.context.getString(R.string.chris_hemsworth)
                        ),
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        ),
                        MovieActorModel(
                            R.drawable.movie_4,
                            view.context.getString(R.string.chris_hemsworth)
                        )
                    )
                ),
                MovieDataModel(
                    "Tenet",
                    R.drawable.movie_tenet,
                    R.drawable.movie_tenet,
                    "16+",
                    "Action, Sci-Fi, Thriller",
                    5,
                    "98 REVIEWS",
                    "Is empty",
                    "97 MIN",
                    true,
                    listOf(
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        ),
                        MovieActorModel(
                            R.drawable.movie_4,
                            view.context.getString(R.string.chris_hemsworth)
                        ),
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        )
                    )
                ),
                MovieDataModel(
                    "Black Widow",
                    R.drawable.movie_black_widow,
                    R.drawable.movie_black_widow,
                    "13+",
                    "Action, Adventure, Sci-Fi",
                    4,
                    "38 REVIEWS",
                    "Это и есть описание фильма",
                    "102 MIN",
                    false,
                    listOf(
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        ),
                        MovieActorModel(
                            R.drawable.movie_4,
                            view.context.getString(R.string.chris_hemsworth)
                        ),
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        )
                    )
                ),
                MovieDataModel(
                    "Wonder Woman 1984",
                    R.drawable.movie_wonder_woman,
                    R.drawable.movie_wonder_woman,
                    "13+",
                    "Action, Adventure, Fantasy",
                    5,
                    "74 REVIEWS",
                    "Is empty",
                    "120 MIN",
                    false,
                    listOf(
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        ),
                        MovieActorModel(
                            R.drawable.movie_4,
                            view.context.getString(R.string.chris_hemsworth)
                        ),
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        )
                    )
                ),
                MovieDataModel(
                    "Фильм 5",
                    R.drawable.movie_list_image1,
                    R.drawable.orig,
                    "21+",
                    view.context.getString(R.string.tag_movie),
                    5,
                    "152 REVIEWS",
                    R.string.description_detail_movie.toString(),
                    "135 MIN",
                    false,
                    listOf(
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        ),
                        MovieActorModel(
                            R.drawable.movie_4,
                            view.context.getString(R.string.chris_hemsworth)
                        ),
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        )
                    )
                ),
                MovieDataModel(
                    "Фильм 6",
                    R.drawable.movie_list_image1,
                    R.drawable.orig,
                    "21+",
                    view.context.getString(R.string.tag_movie),
                    4,
                    "152 REVIEWS",
                    R.string.description_detail_movie.toString(),
                    "135 MIN",
                    false,
                    listOf(
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        ),
                        MovieActorModel(
                            R.drawable.movie_4,
                            view.context.getString(R.string.chris_hemsworth)
                        ),
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        )
                    )
                ),
                MovieDataModel(
                    "Фильм 7",
                    R.drawable.movie_list_image1,
                    R.drawable.orig,
                    "21+",
                    view.context.getString(R.string.tag_movie),
                    3,
                    "152 REVIEWS",
                    R.string.description_detail_movie.toString(),
                    "135 MIN",
                    false,
                    listOf(
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        ),
                        MovieActorModel(
                            R.drawable.movie_4,
                            view.context.getString(R.string.chris_hemsworth)
                        ),
                        MovieActorModel(
                            R.drawable.movie_1,
                            view.context.getString(R.string.robert_downey_jr)
                        ),
                        MovieActorModel(
                            R.drawable.movie_2,
                            view.context.getString(R.string.chris_evans)
                        ),
                        MovieActorModel(
                            R.drawable.movie_3,
                            view.context.getString(R.string.mark_ruffalo)
                        )
                    )
                )
            )
        }
    }
}