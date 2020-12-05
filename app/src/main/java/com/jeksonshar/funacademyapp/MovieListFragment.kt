package com.jeksonshar.funacademyapp

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoviesListFragment: Fragment() {

    private var movieFragmentClickListener: MovieFragmentClickListener? = null
    private var recycler: RecyclerView? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container,false)
//        val movieItem1: ConstraintLayout = view.findViewById(R.id.movieItem1)

//        movieItem1.setOnClickListener {
//            movieFragmentClickListener?.addMovieDetailFragment()
//        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.movieList)
        val movieList = MoviesDataSource.getMovies(view)
        recycler?.adapter = MovieListAdapter(view.context, movieList)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler?.layoutManager = GridLayoutManager(view.context,2)
        } else {
            recycler?.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        }

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