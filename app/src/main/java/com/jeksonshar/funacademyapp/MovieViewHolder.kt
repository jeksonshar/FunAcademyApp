package com.jeksonshar.funacademyapp

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val movieImage = view.findViewById<ImageView>(R.id.movieOfListImage)
    private val ageCategoryMovieView = view.findViewById<TextView>(R.id.ageCategoryMovieListView)
    private val tagMovieView = view.findViewById<TextView>(R.id.tagMovieListView)
    private val ratingMovieView = view.findViewById<RatingBar>(R.id.ratingBarOfMovieList)
    private val reviewsMovieView = view.findViewById<TextView>(R.id.reviewMovieListView)
    private val nameOfMovieView = view.findViewById<TextView>(R.id.nameOfMovieListView)
    private val durationMovieView = view.findViewById<TextView>(R.id.durationMovieView)

    fun onBind(movie: MovieDataModel) {
        movieImage.setImageResource(movie.avatarMovie)
        ageCategoryMovieView.text = movie.ageCategoryMovie
        tagMovieView.text = movie.tagMovie
        ratingMovieView.rating = movie.ratingMovie.toFloat()
        reviewsMovieView.text = movie.numberReviewsMovie
        nameOfMovieView.text = movie.nameMovie
        durationMovieView.text = movie.durationMovie
    }

}