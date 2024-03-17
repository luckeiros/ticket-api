package com.luckeiros.ticketandroid.feature.event.data.repository

import com.luckeiros.ticketandroid.feature.event.domain.mapper.toEventList
import com.luckeiros.ticketandroid.feature.event.data.service.EventService
import com.luckeiros.ticketandroid.feature.event.domain.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

internal class EventRepositoryImpl(private val retrofit: Retrofit) : EventRepository {

    override suspend fun getEvent(city: String): List<Event> =
        withContext(Dispatchers.IO) {
            val service = retrofit.create(EventService::class.java)
            service.getEvent(city).toEventList()
        }
}