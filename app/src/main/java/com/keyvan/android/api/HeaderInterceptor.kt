package com.keyvan.android.api

import android.os.Build
import com.keyvan.android.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created by HosseinBahrami at 4/22/2020
 * Set Header for Requests
 */

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val userAgent =
            "Android ${Build.MANUFACTURER} ${Build.BRAND} ${Build.MODEL} ${Build.VERSION.SDK_INT}"
        val request = chain.request().newBuilder()
            /**
             * SomeTimes No Needed Bearer Word in Authorization
             */
            .addHeader("Authorization", "Bearer ${Constants.ACCESS_TOKEN}")
            .addHeader("Accept", "application/json")
            .addHeader("User-Agent", userAgent)
            .build()
        return chain.proceed(request)
    }
}