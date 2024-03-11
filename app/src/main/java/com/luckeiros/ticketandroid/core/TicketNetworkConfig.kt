package com.luckeiros.ticketandroid.core

import okhttp3.Interceptor
import okhttp3.OkHttpClient

private const val API_KEY = "DW0E98NrxUIfDDtNN7ijruVSm60ryFLX"

val keyInterceptor = Interceptor { chain ->
    val originalRequest = chain.request()
    val originalUrl = originalRequest.url()

    val newUrl = originalUrl.newBuilder().addQueryParameter("apikey", API_KEY)
        .build()

    val newRequest = originalRequest.newBuilder()
        .url(newUrl)
        .build()

    chain.proceed(newRequest)
}

val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(keyInterceptor)
    .build()
