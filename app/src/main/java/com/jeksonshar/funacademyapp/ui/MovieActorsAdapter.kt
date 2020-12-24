package com.jeksonshar.funacademyapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeksonshar.funacademyapp.R
import com.jeksonshar.funacademyapp.data.Actor

class MovieActorsAdapter(var actorList: List<Actor>) :
    RecyclerView.Adapter<MovieActorsViewHolder>() {

    override fun getItemCount(): Int = actorList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieActorsViewHolder {
        return MovieActorsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_actors, parent, false))
    }

    override fun onBindViewHolder(holder: MovieActorsViewHolder, position: Int) {
            holder.onBind(actorList[position])
    }
}