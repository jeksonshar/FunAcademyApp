package com.jeksonshar.funacademyapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jeksonshar.funacademyapp.R
import com.jeksonshar.funacademyapp.data.Actor

class MovieActorsAdapter(var actorList: List<Actor>) :
    RecyclerView.Adapter<MovieActorsViewHolder>() {

    override fun getItemCount(): Int = actorList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieActorsViewHolder {
        return MovieActorsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie_actors, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieActorsViewHolder, position: Int) {
        holder.onBind(actorList[position])
    }
}

class MovieActorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var avatarActors = view.findViewById<ImageView>(R.id.avatarOfActorMovieImage)
    private val nameActors = view.findViewById<TextView>(R.id.nameOfActorMovieView)

    fun onBind(actor: Actor) {

        Glide.with(itemView.context)
            .load(actor.picture)
            .placeholder(R.drawable.image_not_found)
            .apply(RequestOptions())
            .into(avatarActors)

        nameActors.text = actor.name
    }
}