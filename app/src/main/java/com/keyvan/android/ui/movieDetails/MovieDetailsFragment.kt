package com.keyvan.android.ui.movieDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.keyvan.android.R
import com.keyvan.android.api.models.response.MoviesResult
import com.keyvan.android.databinding.FragmentMovieDetailsBinding
import com.keyvan.android.utils.Constants
import com.keyvan.android.utils.baseClasses.BaseFragment
import com.squareup.picasso.Picasso


class MovieDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val moviesResults: MoviesResult by lazy {
        MovieDetailsFragmentArgs.fromBundle(requireArguments()).data
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(Constants.IMAGE_BASE_URL + moviesResults.backdrop_path).into(binding.image)
        binding.rate.rating = moviesResults.vote_average.toFloat()
        binding.playersCount.text = "vote counts: ${moviesResults.vote_count}"
        binding.movieName.text = moviesResults.title
        binding.description.text = moviesResults.overview
        binding.genreName.text = moviesResults.release_date
    }


}