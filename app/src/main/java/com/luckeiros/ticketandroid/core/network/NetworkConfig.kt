package com.luckeiros.ticketandroid.core.network

import com.luckeiros.ticketandroid.BuildConfig
import java.util.concurrent.TimeUnit

object NetworkConfig {
    const val baseUrl = BuildConfig.BASE_URL

    object Timeout {
        const val connectTimeout = BuildConfig.CONNECT_TIMEOUT
        const val readTimeout = BuildConfig.READ_TIMEOUT
        const val writeTimeout = BuildConfig.WRITE_TIMEOUT
        val defaultTimeUnit = TimeUnit.SECONDS
    }
}
