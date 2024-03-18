package com.luckeiros.ticketandroid.core.application

import android.app.Application
import com.luckeiros.ticketandroid.core.network.networkModule
import com.luckeiros.ticketandroid.feature.event.di.eventModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TicketApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(baseContext)
            modules(networkModule, eventModule)
        }
    }
}