package com.jeksonshar.funacademyapp

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeksonshar.funacademyapp.data.MovieDataModel
import com.jeksonshar.funacademyapp.data.MoviesDataSource
import java.util.*

class MoviesListFragment: Fragment() {

//    private var clickListener: MovieFragmentClickListener? = null
    private var recycler: RecyclerView? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.movieList)
        val movieList = MoviesDataSource.getMovies(view)
        recycler?.adapter = MovieListAdapter(clickListener, movieList)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler?.layoutManager = GridLayoutManager(view.context,2)
        } else {
            recycler?.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieFragmentClickListener) {
            clickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    private var clickListener: MovieFragmentClickListener? = object : MovieFragmentClickListener {
        override fun addMovieDetailFragment(movie: MovieDataModel) {
            fragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.fragment_container, MovieDetailsFragment.newInstance(movie.nameMovie))
                ?.commit()
        }

        override fun changeFavoriteValue(movie: MovieDataModel) {
            movie.isFavorite = !movie.isFavorite
        }
    }
}