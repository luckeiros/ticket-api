package com.luckeiros.ticketandroid.feature.event.di

import com.luckeiros.ticketandroid.core.okHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val eventsModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

private const val BASE_URL = "https://app.ticketmaster.com/discovery/v2/"