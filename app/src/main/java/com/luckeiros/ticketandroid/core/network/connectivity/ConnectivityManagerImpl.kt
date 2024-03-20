package com.luckeiros.ticketandroid.core.network.connectivity

import android.content.Context

class ConnectivityManagerImpl(private val context: Context) : ConnectivityManager {

    override fun isConnectionAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager

        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}