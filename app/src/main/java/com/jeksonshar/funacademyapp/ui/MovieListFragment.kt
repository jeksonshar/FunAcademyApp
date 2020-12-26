package com.jeksonshar.funacademyapp.ui

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
import com.jeksonshar.funacademyapp.R
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.data.loadMovies
import kotlinx.coroutines.*

class MoviesListFragment: Fragment() {

    private var recycler: RecyclerView? = null
    private var scope = CoroutineScope(Dispatchers.IO + Job())

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.movieList)

        scope.launch {
            val deffer = scope.async { loadMovies(requireContext()) }
            val movieList: List<Movie> = deffer.await()
            scope.launch(Dispatchers.Main) {
                recycler?.adapter = MovieListAdapter(clickListener, movieList)
            }
        }

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

        override fun addMovieDetailFragment(movie: Movie) {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(
                    R.id.fragment_container,
                    MovieDetailsFragment.newInstance(movie.id, movie.isFavorite)
                )
                .commit()
        }

        override fun changeFavoriteValue(movie: Movie) {
            movie.isFavorite = !movie.isFavorite
        }
    }
}