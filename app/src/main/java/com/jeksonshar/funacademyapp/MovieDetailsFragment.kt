package com.jeksonshar.funacademyapp

import android.os.Bundle
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
import com.jeksonshar.funacademyapp.data.MovieDataModel
import com.jeksonshar.funacademyapp.data.MoviesDataSource

class MovieDetailsFragment : Fragment() {

    private lateinit var currentMovie: MovieDataModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameMovie: String? = arguments?.getString(MOVIE_KEY)
        val movieList: List<MovieDataModel> = MoviesDataSource.getMovies(view)
        for (movie: MovieDataModel in movieList) {
            if (movie.nameMovie == nameMovie) {
                currentMovie = movie
            }
        }

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

        avatarMovieView.setImageResource(currentMovie.avatarMovieForDetail)
        ageCategoryMovie.text = currentMovie.ageCategoryMovie
        nameOfMovie.text = currentMovie.nameMovie
        tagMovie.text = currentMovie.tagMovie
        ratingBar.rating = currentMovie.ratingMovie.toFloat()
        reviewsMovie.text = currentMovie.numberReviewsMovie
        descriptionMovie.text = currentMovie.descriptionMovies
        recycler.adapter = MovieActorsAdapter(currentMovie.actorsMovie)

        recycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
    }

    companion object {
        private const val MOVIE_KEY = "MovieItem"
        fun newInstance(name: String): MovieDetailsFragment {
            val args = Bundle()
            args.putString(MOVIE_KEY, name)
            val fragment = MovieDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}

