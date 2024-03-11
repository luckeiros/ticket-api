package com.luckeiros.ticketandroid

import android.app.Application
import com.luckeiros.ticketandroid.feature.event.di.eventsModule
import com.luckeiros.ticketandroid.feature.event.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TicketApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(baseContext)
            modules(networkModule, eventsModule)
        }
    }
}