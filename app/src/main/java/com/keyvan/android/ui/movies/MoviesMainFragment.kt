package com.keyvan.android.ui.movies

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import java.util.*


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

        initSearch()
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
        initLoading(binding.loading, true)
        moviesViewMode.getMovies(1)
        moviesViewMode.getMoviesData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is MoviesBean -> {
                    adapter.addItem(it.results)
                    initLoading(binding.loading, false)
                }

                is MoviesErr -> {
                    showLongToast("Check Your VPN" + "\n" + "\n" + it.errors.toString())
                    Log.v("retrofitErr", it.errors.toString())
                    initLoading(binding.loading, false)
                }
            }
        })
    }


    private fun searchMovies(page: Int, query: String) {
        initLoading(binding.loading, true)
        moviesViewMode.searchMovies(page, query)
        moviesViewMode.getSearchMoviesData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is MoviesBean -> {
                    adapter.updateItem(it.results)
                    initLoading(binding.loading, false)
                }

                is MoviesErr -> {
                    showLongToast("Check Your VPN" + "\n" + "\n" + it.errors.toString())
                    Log.v("retrofitErr", it.errors.toString())
                    initLoading(binding.loading, false)
                }
            }
        })
    }


    private fun initSearch() {
        binding.inputSearch.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                private var timer: Timer = Timer()
                private val DELAY: Long = 1000 // milliseconds
                override fun afterTextChanged(s: Editable) {
                    timer.cancel()
                    timer = Timer()
                    timer.schedule(
                        object : TimerTask() {
                            override fun run() {
                                requireActivity().runOnUiThread {
                                    if (binding.inputSearch.text.toString().isEmpty())
                                        getMovies()
                                    else
                                        searchMovies(
                                            1,
                                            binding.inputSearch.text.toString()
                                        )
                                }
                            }
                        },
                        DELAY
                    )
                }
            }
        )
    }

}