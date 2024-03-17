package com.luckeiros.ticketandroid.core.network

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal val networkModule = module {
    val defaultTimeUnit = TimeUnit.SECONDS

    single {
        OkHttpClient.Builder()
            .addInterceptor(NetworkConfig.Interceptor.keyInterceptor)
            .connectTimeout(NetworkConfig.Timeout.connectTimeout, defaultTimeUnit)
            .readTimeout(NetworkConfig.Timeout.readTimeout, defaultTimeUnit)
            .writeTimeout(NetworkConfig.Timeout.writeTimeout, defaultTimeUnit)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(NetworkConfig.baseUrl)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}