package com.jeksonshar.funacademyapp.view

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jeksonshar.funacademyapp.R
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.db.FavoriteSharedPreferences

class MovieListAdapter(
    private val listener: MovieFragmentClickListener?,
    private var movieList: List<Movie>
) : RecyclerView.Adapter<MovieListViewHolder>() {

    override fun getItemCount(): Int = movieList.size

    private fun getItem(position: Int): Movie = movieList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener {
            listener?.addMovieDetailFragment(getItem(position))
        }
        holder.itemView.findViewById<LinearLayout>(R.id.isFavorite).setOnClickListener {
            listener?.changeFavoriteValue(getItem(position))
            notifyDataSetChanged()
        }
    }
}

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
            .placeholder(R.drawable.image_not_found)
            .apply(RequestOptions())
            .into(movieImage)

        var tmp = "${movie.minimumAge}+"
        ageCategoryMovieView.text = tmp
        tagMovieView.text = movie.genres.joinToString { it.name }
        ratingMovieView.rating = movie.ratings / 2
        tmp = "${movie.numberOfRatings}  ${itemView.resources.getString(R.string.reviews)}"
        reviewsMovieView.text = tmp
        nameOfMovieView.text = movie.title
        tmp = "${movie.runtime} ${itemView.resources.getString(R.string.min)}"
        durationMovieView.text = tmp

        val listShared = FavoriteSharedPreferences.listFavorite
        for (item: SharedPreferences in listShared) {
            if (item.contains("${movie.id}"))
                movie.isFavorite = item.getBoolean("${movie.id}", false)
        }

        if (movie.isFavorite) {
            isLiked.setImageResource(R.drawable.like_on)
        } else {
            isLiked.setImageResource(R.drawable.like_off)
        }
    }
}