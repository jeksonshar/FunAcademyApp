package com.jeksonshar.funacademyapp.ui

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jeksonshar.funacademyapp.R
import com.jeksonshar.funacademyapp.data.Movie

class MovieListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val movieImage = view.findViewById<ImageView>(R.id.movieOfListImage)
    private val ageCategoryMovieView = view.findViewById<TextView>(R.id.ageCategoryMovieListView)
    private val tagMovieView = view.findViewById<TextView>(R.id.tagMovieListView)
    private val ratingMovieView = view.findViewById<RatingBar>(R.id.ratingBarOfMovieList)
    private val reviewsMovieView = view.findViewById<TextView>(R.id.reviewMovieListView)
    private val nameOfMovieView = view.findViewById<TextView>(R.id.nameOfMovieListView)
    private val durationMovieView = view.findViewById<TextView>(R.id.durationMovieView)
    private val isLiked = view.findViewById<ImageView>(R.id.favoriteImage)

    fun onBind(movie: Movie) {

        Glide.with(itemView.context)
            .load(movie.poster)
            .apply(RequestOptions())
            .into(movieImage)

        var tmp = "${movie.minimumAge}+"
        ageCategoryMovieView.text = tmp
        tagMovieView.text = movie.genres.joinToString { it.name }
        ratingMovieView.rating = movie.ratings/2
        tmp = "${movie.numberOfRatings} REVIEWS"
        reviewsMovieView.text = tmp
        nameOfMovieView.text = movie.title
        tmp = "${movie.runtime} MIN"
        durationMovieView.text = tmp

        if (movie.isFavorite) {
            isLiked.setImageResource(R.drawable.like_on)
        } else {
            isLiked.setImageResource(R.drawable.like_off)
        }
    }
}