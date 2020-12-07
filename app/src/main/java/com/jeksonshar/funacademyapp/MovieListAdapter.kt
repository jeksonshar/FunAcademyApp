package com.jeksonshar.funacademyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.jeksonshar.funacademyapp.data.MovieDataModel

class MovieListAdapter(
    private val listener: MovieFragmentClickListener?,
    var movieList: List<MovieDataModel>
    ) : RecyclerView.Adapter<MovieListViewHolder>() {

    override fun getItemCount(): Int = movieList.size

    private fun getItem(position: Int): MovieDataModel = movieList[position]


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_list, parent, false))
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