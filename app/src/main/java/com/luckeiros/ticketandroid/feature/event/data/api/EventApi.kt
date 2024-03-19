package com.luckeiros.ticketandroid.feature.event.data.api

import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApi {
    @GET(EVENTS_PATH)
    suspend fun getEvents(@Query("city") city: String?, @Query("page") page: Int): EventResponseDTO

    private companion object {
        const val EVENTS_PATH = "events.json"
    }
}