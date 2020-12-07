package com.jeksonshar.funacademyapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeksonshar.funacademyapp.data.MovieActorModel

class MovieActorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val avatarActors = view.findViewById<ImageView>(R.id.avatarOfActorMovieImage)
    private val nameActors = view.findViewById<TextView>(R.id.nameOfActorMovieView)

    fun onBind(actor: MovieActorModel) {
        avatarActors.setImageResource(actor.actorAvatar)
        nameActors.text = actor.actorName
    }
}