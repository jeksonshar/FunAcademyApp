package com.jeksonshar.funacademyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), MovieFragmentClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, MoviesListFragment(), CURRENT_FRAGMENT_TAG)
                .commit()
        } else {
            supportFragmentManager.findFragmentByTag(CURRENT_FRAGMENT_TAG)
        }
    }

    override fun addMovieDetailFragment() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragment_container, MoviesDetailsFragment())
            .commit()
    }

    companion object {
        const val CURRENT_FRAGMENT_TAG = "CurrentFragment"
    }
}