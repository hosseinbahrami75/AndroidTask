package com.keyvan.android.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keyvan.android.R
import com.keyvan.android.adapters.MoviesAdapter
import com.keyvan.android.api.models.response.MoviesBean
import com.keyvan.android.api.models.response.MoviesErr
import com.keyvan.android.databinding.FragmentMoviesBinding
import com.keyvan.android.utils.baseClasses.BaseFragment

class MoviesMainFragment : BaseFragment() {
    private lateinit var binding: FragmentMoviesBinding
    private val moviesViewMode: MoviesViewModel by viewModels()
    private lateinit var adapter: MoviesAdapter

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

        initMovieList()
        getMovies()
    }

    private fun initMovieList() {
        adapter = MoviesAdapter(requireContext()) {
            findNavController().navigate(
                MoviesMainFragmentDirections.actionMoviesFragmentToMovieDetails(
                    it
                )
            )
        }
        val layoutManger = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.moviesList.layoutManager = layoutManger
        binding.moviesList.adapter = adapter
    }

    private fun getMovies() {
        moviesViewMode.getMovies("1")
        moviesViewMode.getMoviesData().observe(viewLifecycleOwner, Observer {
            when(it) {
                is  MoviesBean -> {
                    adapter.addItem(it.results)
                }

                is MoviesErr -> {
                    showShortToast(it.errors.toString())
                    Log.v("retrofitErr", it.errors.toString())
                }
            }
        })
    }
}