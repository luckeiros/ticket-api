package com.luckeiros.ticketandroid.feature.event.di

import com.luckeiros.ticketandroid.core.keyInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module

val networkModule = module{
    single {
        OkHttpClient.Builder()
            .addInterceptor(keyInterceptor)
            .build()
    }
}