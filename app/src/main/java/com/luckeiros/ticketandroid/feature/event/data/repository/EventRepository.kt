package com.luckeiros.ticketandroid.feature.event.data.repository

import com.luckeiros.ticketandroid.feature.event.domain.Events

interface EventRepository {
    suspend fun getEvents(city: String, page: Int): Events
}