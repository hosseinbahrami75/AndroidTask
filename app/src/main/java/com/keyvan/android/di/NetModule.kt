package com.keyvan.android.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.keyvan.android.api.ApiCall
import com.keyvan.android.api.HeaderInterceptor
import com.keyvan.android.utils.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetModule {
    val netModule = module {
        single<ApiCall> {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
                        .connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                        .readTimeout(Constants.READ_TIME_OUT, TimeUnit.SECONDS)
                        .writeTimeout(Constants.WRITE_TIME_OUT, TimeUnit.SECONDS)
                        .build()
                )
                .build()
            retrofit.create(ApiCall::class.java)
        }
    }
}