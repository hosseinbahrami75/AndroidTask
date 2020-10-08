package com.keyvan.android.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.keyvan.android.R
import com.keyvan.android.api.models.response.MoviesBean
import com.keyvan.android.api.models.response.MoviesErr
import com.keyvan.android.databinding.FragmentMoviesBinding
import com.keyvan.android.utils.baseClasses.BaseFragment

class MoviesMainFragment : BaseFragment() {
    private lateinit var binding: FragmentMoviesBinding
    private val moviesViewMode: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovies()
    }


    private fun getMovies() {
        moviesViewMode.getMovies("1")
        moviesViewMode.getMoviesData().observe(viewLifecycleOwner, Observer {
            when(it) {
                is  MoviesBean -> {
                    showShortToast(it.results[1].title)

                }

                is MoviesErr -> {
                    showShortToast(it.errors.toString())
                    Log.v("retrofitErr", it.errors.toString())
                }
            }
        })
    }
}