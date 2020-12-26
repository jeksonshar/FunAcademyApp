package com.jeksonshar.funacademyapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeksonshar.funacademyapp.R
import com.jeksonshar.funacademyapp.data.Actor
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.data.loadMovies
import kotlinx.coroutines.*

class MovieDetailsFragment : Fragment() {

    private lateinit var currentMovie: Movie
    private var scope = CoroutineScope(Dispatchers.IO)

    companion object {
        private const val MOVIE_KEY = "MovieItem"
        private const val MOVIE_KEY_FAVORITE = "MovieIsFavorite"

        fun newInstance(id: Int, isFavorite: Boolean): MovieDetailsFragment {
            val args = Bundle()
            args.putInt(MOVIE_KEY, id)
            args.putBoolean(MOVIE_KEY_FAVORITE, isFavorite)
            val fragment = MovieDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idMovie: Int? = arguments?.getInt(MOVIE_KEY)
        val isFavoriteMovie: Boolean = arguments?.getBoolean(MOVIE_KEY_FAVORITE) == true

        scope.launch {
            val deffer = scope.async { loadMovies(requireContext()) }
            val movieList: List<Movie> = deffer.await()

            for (movie: Movie in movieList) {
                if (movie.id == idMovie) {
                    currentMovie = movie
                    currentMovie.isFavorite = isFavoriteMovie
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler: RecyclerView = view.findViewById(R.id.movieListDetail)

        val backButton = view.findViewById<LinearLayout>(R.id.backButton)
        val avatarMovieView = view.findViewById<ImageView>(R.id.avatarMovieImage)
        val ageCategoryMovie = view.findViewById<TextView>(R.id.ageCategoryMovieView)
        val nameOfMovie = view.findViewById<TextView>(R.id.nameMovieView)
        val tagMovie = view.findViewById<TextView>(R.id.tagMovieView)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBarOfMovie)
        val reviewsMovie = view.findViewById<TextView>(R.id.reviewsMovieView)
        val descriptionMovie = view.findViewById<TextView>(R.id.descriptionMovieView)

        backButton.setOnClickListener {
            activity?.onBackPressed()
        }

        Glide.with(requireContext())
            .load(currentMovie.backdrop)
            .into(avatarMovieView)

        var tmp = "${currentMovie.minimumAge}+"
        ageCategoryMovie.text = tmp
        nameOfMovie.text = currentMovie.title
        tagMovie.text = currentMovie.genres.joinToString { it.name }
        ratingBar.rating = currentMovie.ratings / 2
        tmp = "${currentMovie.numberOfRatings} REVIEWS"
        reviewsMovie.text = tmp
        descriptionMovie.text = currentMovie.overview

        // дополнительный вариант обработки пустого списка актеров
//        if (currentMovie.actors.isEmpty()) {
//            val tmp = currentMovie.actors.toMutableList()
//            tmp.add(Actor(1, "Not found actors", ""))
//            val tmp1 = tmp.toList()
//            recycler.adapter = MovieActorsAdapter(tmp1)
//        } else {
//            recycler.adapter = MovieActorsAdapter(currentMovie.actors)
//        }
//        recycler.layoutManager = LinearLayoutManager(
//            view.context,
//            LinearLayoutManager.HORIZONTAL,
//            false
//        )

        if (currentMovie.actors.isEmpty()) {
            view.findViewById<TextView>(R.id.castMovieView).visibility = View.GONE
            recycler.visibility = View.GONE
        } else {
            recycler.adapter = MovieActorsAdapter(currentMovie.actors)
            recycler.layoutManager = LinearLayoutManager(
                view.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }
}