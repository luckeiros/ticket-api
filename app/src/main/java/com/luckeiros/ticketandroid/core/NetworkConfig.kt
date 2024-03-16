package com.luckeiros.ticketandroid.core

import com.luckeiros.ticketandroid.BuildConfig
import okhttp3.Interceptor

object NetworkConfig {
    const val baseUrl = BuildConfig.BASE_URL

    object Interceptor {
        private const val API_KEY = "DW0E98NrxUIfDDtNN7ijruVSm60ryFLX"

        val keyInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url()

            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("apikey", API_KEY)
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    object Timeout {
        const val connectTimeout = BuildConfig.CONNECT_TIMEOUT
        const val readTimeout = BuildConfig.READ_TIMEOUT
        const val writeTimeout = BuildConfig.WRITE_TIMEOUT
    }
}
