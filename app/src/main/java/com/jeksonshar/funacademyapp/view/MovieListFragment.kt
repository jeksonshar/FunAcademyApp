package com.jeksonshar.funacademyapp.view

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.jeksonshar.funacademyapp.R
import com.jeksonshar.funacademyapp.background.MovieUpdateRepository
import com.jeksonshar.funacademyapp.db.FavoriteSharedPreferences
import com.jeksonshar.funacademyapp.db.RepositoryProvider
import com.jeksonshar.funacademyapp.data.Movie
import com.jeksonshar.funacademyapp.viewModels.MovieListViewModel
import com.jeksonshar.funacademyapp.viewModels.MovieListViewModelFactory

class MoviesListFragment : Fragment() {

    private var recycler: RecyclerView? = null
    private lateinit var viewModel: MovieListViewModel
    var savedIsFavorite: FavoriteSharedPreferences? = null
    private var noInternetDialog: NoInternetConnectionListDialog? = null

    companion object {
        const val KEY_DIALOG_NO_INTERNET = "key_dialog_no_internet"
        const val UNIQUE_WORK_NAME = "uniqueWorkName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MovieListViewModelFactory(requireActivity().application)
        ).get(MovieListViewModel::class.java)

        savedIsFavorite = RepositoryProvider.getInstanceFavoriteMovies(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.movieList)

        if (!isConnectionAble()) {
            showDialogNoInternetConnection(savedInstanceState)
        }

        viewModel.moviesLiveData.observe(this.viewLifecycleOwner) {
            recycler?.adapter = MovieListAdapter(clickListener, it)
        }

        viewModel.observeMoviesUpdates().observe(this.viewLifecycleOwner, {
            MovieListAdapter(clickListener, it).submitList(it)
        })

        /** извлечения значений из SharedPreferences при запуске App */
        savedIsFavorite?.getFavoriteMovies()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler?.layoutManager = GridLayoutManager(view.context, 2)
        } else {
            recycler?.layoutManager =
                LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        }

        WorkManager.getInstance(requireContext()).cancelAllWork()
        WorkManager.getInstance(requireContext()).enqueue(MovieUpdateRepository().movieUpdateWorker)
//        WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork(
//            UNIQUE_WORK_NAME,
//            ExistingPeriodicWorkPolicy.KEEP,
//            MovieUpdateRepository().movieUpdateWorker
//        )
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(KEY_DIALOG_NO_INTERNET, noInternetDialog)
    }

    private fun isConnectionAble(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

    fun showDialogNoInternetConnection(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            noInternetDialog = NoInternetConnectionListDialog()
            val noInternetManager = requireActivity().supportFragmentManager
            noInternetDialog?.show(
                noInternetManager,
                context?.resources?.getString(R.string.no_internet_dialog_tag)
            )
        } else {
            noInternetDialog = savedInstanceState
                .getSerializable(KEY_DIALOG_NO_INTERNET) as NoInternetConnectionListDialog
        }
    }

    private var clickListener: MovieFragmentClickListener? = object : MovieFragmentClickListener {

        override fun addMovieDetailFragment(movie: Movie) {
            if (!isConnectionAble()) {
                showDialogNoInternetConnection(null)
            }
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(
                    R.id.fragment_container,
                    MovieDetailsFragment.newInstance(movie.id)
                )
                .commit()
        }

        override fun changeFavoriteValue(movie: Movie) {
            movie.isFavorite = !movie.isFavorite

            /** сохранения значений в SharedPreferences если лайк, удаление если снять лайк */
            if (movie.isFavorite) {
                savedIsFavorite?.saveFavoriteMovie(movie)
            } else {
                savedIsFavorite?.deleteFavoriteMovie(movie)
            }
        }
    }
}