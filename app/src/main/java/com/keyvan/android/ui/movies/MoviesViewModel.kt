package com.keyvan.android.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyvan.android.api.models.response.MoviesData
import org.koin.java.KoinJavaComponent.inject

class MoviesViewModel: ViewModel() {
    private val repository by inject(MoviesRepository::class.java)

    private var moviesData = MutableLiveData<MoviesData>()
    fun getMoviesData(): MutableLiveData<MoviesData> = moviesData
    fun getMovies(page: String) {
        moviesData = repository.getMovies(page)
    }
}