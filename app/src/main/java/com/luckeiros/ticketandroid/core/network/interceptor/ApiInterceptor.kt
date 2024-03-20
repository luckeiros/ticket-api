package com.luckeiros.ticketandroid.core.network.interceptor

import com.luckeiros.ticketandroid.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object ApiInterceptor : Interceptor {
    private const val API_KEY = BuildConfig.API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("apikey", API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}