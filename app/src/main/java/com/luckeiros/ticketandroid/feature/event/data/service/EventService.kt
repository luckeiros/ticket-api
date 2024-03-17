package com.luckeiros.ticketandroid.feature.event.data.service

import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

internal interface EventService {
    @GET(EVENTS_PATH)
    suspend fun getEvent(@Query("city") city: String): EventResponseDTO

    private companion object {
        const val EVENTS_PATH = "events.json"
    }
}