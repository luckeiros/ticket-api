package com.luckeiros.ticketandroid.core.network.interceptor

import com.luckeiros.ticketandroid.core.network.NoConnectionException
import com.luckeiros.ticketandroid.core.network.connectivity.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val connectivityManager: ConnectivityManager) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectivityManager.isConnectionAvailable()) {
            throw NoConnectionException()
        } else {
            return chain.proceed(chain.request())
        }
    }
}