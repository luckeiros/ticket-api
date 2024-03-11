package com.luckeiros.ticketandroid.feature.event.data.mapper

import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventResponseDTO
import com.luckeiros.ticketandroid.feature.event.domain.Event

fun EventResponseDTO.toEventList(): List<Event> =
    embedded?.events?.map { eventDTO ->
        eventDTO.toModel()
    } ?: emptyList()

fun EventDTO.toModel(): Event {
    val venue = embedded?.venues?.firstOrNull()

    return Event(
        name = name.orEmpty(),
        date = dates?.start?.localDate.orEmpty(),
        time = dates?.start?.dateTime.orEmpty(),
        venueName = venue?.name.orEmpty(),
        city = venue?.city?.name.orEmpty(),
        state = venue?.state?.stateCode.orEmpty(),
        imageUrl = images?.map { it.url }?.firstOrNull().orEmpty()
    )
}