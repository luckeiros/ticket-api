package com.luckeiros.ticketandroid.feature.event.data.repository

import com.luckeiros.ticketandroid.feature.event.data.api.EventApi
import com.luckeiros.ticketandroid.feature.event.data.remote.mapper.toModel
import com.luckeiros.ticketandroid.feature.event.domain.Events
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class EventRepositoryImpl(private val api: EventApi) : EventRepository {

    override suspend fun getEvents(city: String?, page: Int): Events =
        withContext(Dispatchers.IO) {
            api.getEvents(city, page).toModel()
        }
}