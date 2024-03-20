package com.luckeiros.ticketandroid.core.network

import com.luckeiros.ticketandroid.core.network.NetworkConfig.Timeout.connectTimeout
import com.luckeiros.ticketandroid.core.network.NetworkConfig.Timeout.defaultTimeUnit
import com.luckeiros.ticketandroid.core.network.NetworkConfig.Timeout.readTimeout
import com.luckeiros.ticketandroid.core.network.NetworkConfig.Timeout.writeTimeout
import com.luckeiros.ticketandroid.core.network.NetworkConfig.baseUrl
import com.luckeiros.ticketandroid.core.network.connectivity.ConnectivityManager
import com.luckeiros.ticketandroid.core.network.connectivity.ConnectivityManagerImpl
import com.luckeiros.ticketandroid.core.network.interceptor.ApiInterceptor
import com.luckeiros.ticketandroid.core.network.interceptor.ConnectivityInterceptor
import com.luckeiros.ticketandroid.core.network.interceptor.ErrorInterceptor
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor)
            .addInterceptor(get<ConnectivityInterceptor>())
            .addInterceptor(ErrorInterceptor)
            .connectTimeout(connectTimeout, defaultTimeUnit)
            .readTimeout(readTimeout, defaultTimeUnit)
            .writeTimeout(writeTimeout, defaultTimeUnit)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factoryOf(::ConnectivityManagerImpl) bind ConnectivityManager::class

    singleOf(::ConnectivityInterceptor)
}