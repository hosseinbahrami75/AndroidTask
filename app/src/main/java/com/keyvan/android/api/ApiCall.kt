package com.keyvan.android.api

import com.keyvan.android.api.models.response.MoviesBean
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

     //GetMovies
     @GET("3/discover/movie?api_key=59ef0aed8a8ebcd7164d2a7507b1303b&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
     fun getMoviesAsync(
//         @Query("page") page: String
     ): Deferred<MoviesBean>


    //Search in Movies
    @GET("3/discover/movie?api_key=59ef0aed8a8ebcd7164d2a7507b1303b&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false")
    fun searchInMoviesAsync(
        @Query("page") page: String,
        @Query("query") query: String
    ): Deferred<MoviesBean>
}