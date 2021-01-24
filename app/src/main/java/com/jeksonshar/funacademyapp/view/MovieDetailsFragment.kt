package com.jeksonshar.funacademyapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeksonshar.funacademyapp.R
import com.jeksonshar.funacademyapp.viewModels.MovieDetailsViewModel
import com.jeksonshar.funacademyapp.viewModels.MovieDetailsViewModelFactory

class MovieDetailsFragment : Fragment() {

    private lateinit var viewModel: MovieDetailsViewModel

    companion object {
        private const val MOVIE_KEY = "MovieItem"

        fun newInstance(id: Int): MovieDetailsFragment {
            val args = Bundle()
            args.putInt(MOVIE_KEY, id)
            val fragment = MovieDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idMovie: Int = requireArguments().getInt(MOVIE_KEY)

        viewModel = ViewModelProvider(
            this,
            MovieDetailsViewModelFactory(idMovie)
        ).get(MovieDetailsViewModel::class.java)
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

        viewModel.currentMovieLiveData.observe(this.viewLifecycleOwner) { movie ->
            Glide.with(requireContext())
                .load(movie.backdrop)
                .into(avatarMovieView)

            var tmp = "${movie.minimumAge}+"
            ageCategoryMovie.text = tmp
            nameOfMovie.text = movie.title
            tagMovie.text = movie.genres.joinToString { it.name }
            ratingBar.rating = movie.ratings / 2
            tmp = "${movie.numberOfRatings}  ${view.resources.getString(R.string.reviews)}"
            reviewsMovie.text = tmp
            descriptionMovie.text = movie.overview

            /**  в случае, когда список актеров пуст, скрываем TextView - Cast и RecyclerView,
            если же список актеров имеется - выводим его в RecyclerView                 */
            if (movie.actors.isEmpty()) {
                view.findViewById<TextView>(R.id.castMovieView).visibility = View.GONE
                recycler.visibility = View.GONE
            } else {
                recycler.adapter = MovieActorsAdapter(movie.actors)
                recycler.layoutManager = LinearLayoutManager(
                    view.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }
        }
    }
}