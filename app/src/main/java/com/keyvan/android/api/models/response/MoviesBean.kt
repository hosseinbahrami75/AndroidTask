package com.keyvan.android.api.models.response

sealed class MoviesData
data class MoviesBean(
    val page: Int,
    val results: List<MoviesResult>,
    val total_pages: Int,
    val total_results: Int
) : MoviesData()

data class MoviesErr(val errors: List<String>) : MoviesData()