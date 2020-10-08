package com.keyvan.android.ui.movies

import androidx.lifecycle.MutableLiveData
import com.keyvan.android.api.ApiCall
import com.keyvan.android.api.CallRetrofit
import com.keyvan.android.api.models.response.MoviesData
import com.keyvan.android.api.models.response.MoviesErr
import org.koin.java.KoinJavaComponent.inject

class MoviesRepository {
    private val apiCall by inject(ApiCall::class.java)

    fun getMovies(page: String): MutableLiveData<MoviesData> {
        val data = MutableLiveData<MoviesData>()
        CallRetrofit.callApi(apiCall.getMoviesAsync(), {
            data.value = it
        }, {
            data.value = MoviesErr(listOf(it.msg))
        })
        return data
    }

}