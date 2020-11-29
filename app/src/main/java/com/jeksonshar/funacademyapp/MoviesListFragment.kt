package com.jeksonshar.funacademyapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class MoviesListFragment: Fragment() {

    private var movieFragmentClickListener: MovieFragmentClickListener? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container,false)
        val movieItem1: ConstraintLayout = view.findViewById(R.id.movieItem1)

        movieItem1.setOnClickListener {
            movieFragmentClickListener?.addMovieDetailFragment()
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieFragmentClickListener) {
            movieFragmentClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        movieFragmentClickListener = null
    }
}

interface MovieFragmentClickListener {
    fun addMovieDetailFragment()
}