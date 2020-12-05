package com.jeksonshar.funacademyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieListAdapter(
    context: Context,
    var movieList: List<MovieDataModel>
    ) : RecyclerView.Adapter<MovieViewHolder>() {

        private var inflater = LayoutInflater.from(context)

    override fun getItemCount(): Int = movieList.size

    private fun getItem(position: Int): MovieDataModel = movieList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }



//    override fun getItemId(position: Int): Long = movieList[position].idMovie.hashCode().toLong()
}