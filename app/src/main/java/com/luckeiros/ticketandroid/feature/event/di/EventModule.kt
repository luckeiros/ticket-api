package com.luckeiros.ticketandroid.feature.event.di

import com.luckeiros.ticketandroid.feature.event.data.repository.EventRepository
import com.luckeiros.ticketandroid.feature.event.data.repository.EventRepositoryImpl
import com.luckeiros.ticketandroid.feature.event.data.api.EventApi
import com.luckeiros.ticketandroid.feature.event.presentation.viewmodel.EventViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

internal val eventModule = module {
    factory { get<Retrofit>().create(EventApi::class.java) }
    factoryOf(::EventRepositoryImpl) bind EventRepository::class
    factoryOf(::EventViewModel)
}