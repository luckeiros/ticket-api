package com.luckeiros.ticketandroid.feature.event.di

import com.luckeiros.ticketandroid.feature.event.data.repository.EventRepository
import com.luckeiros.ticketandroid.feature.event.data.repository.EventRepositoryImpl
import com.luckeiros.ticketandroid.feature.event.data.service.EventService
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

internal val eventModule = module {
    factory { get<Retrofit>().create(EventService::class.java) }
    factoryOf(::EventRepositoryImpl) bind EventRepository::class
}