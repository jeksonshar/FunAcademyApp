package com.jeksonshar.funacademyapp.ui


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeksonshar.funacademyapp.R
import com.jeksonshar.funacademyapp.data.Actor

class MovieActorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var avatarActors = view.findViewById<ImageView>(R.id.avatarOfActorMovieImage)
    private val nameActors = view.findViewById<TextView>(R.id.nameOfActorMovieView)

    fun onBind(actor: Actor) {

        Glide.with(itemView.context)
            .load(actor.picture)
            .into(avatarActors)

        nameActors.text = actor.name
    }
}