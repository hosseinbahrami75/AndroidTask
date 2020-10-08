package com.keyvan.android.di

import com.keyvan.android.ui.movieDetails.MovieDetailsRepository
import com.keyvan.android.ui.movies.MoviesRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        single { MoviesRepository() }
        single { MovieDetailsRepository() }
    }
}