package com.luckeiros.ticketandroid.feature.event.data.repository

import com.luckeiros.ticketandroid.feature.event.domain.Event

internal interface EventRepository {
    suspend fun getEvent(cities: String): List<Event>
}