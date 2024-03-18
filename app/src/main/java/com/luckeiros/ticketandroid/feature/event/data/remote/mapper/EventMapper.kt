package com.luckeiros.ticketandroid.feature.event.data.remote.mapper

import com.luckeiros.ticketandroid.common.extension.orZero
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventImageDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventResponseDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.PageDTO
import com.luckeiros.ticketandroid.feature.event.domain.Event
import com.luckeiros.ticketandroid.feature.event.domain.EventImage
import com.luckeiros.ticketandroid.feature.event.domain.Page


fun EventResponseDTO.toModel(): List<Event> =
    embedded?.events?.map { eventDTO ->
        val name = eventDTO.name
        val date = eventDTO.dates?.start?.localDate
        val venues = eventDTO.embedded?.venues
        val venue = venues?.firstOrNull()?.name
        val city = venues?.firstOrNull()?.city?.name
        val stateCode = venues?.firstOrNull()?.state?.stateCode
        val location = if (stateCode?.isNotEmpty() == true) "$city, $stateCode" else city
        val images = eventDTO.images?.map { it.toModel() }

        Event(
            name = name.orEmpty(),
            date = date.orEmpty(),
            venue = venue.orEmpty(),
            location = location.orEmpty(),
            images = images ?: emptyList()
        )
    } ?: emptyList()


private fun EventImageDTO.toModel() = EventImage(
    url = url.orEmpty(),
    ratio = ratio.orEmpty(),
    width = width.orZero(),
    height = height.orZero()
)

private fun PageDTO.toModel() = Page(
    size = size.orZero(),
    totalPages = totalPages.orZero(),
    number = number.orZero()
)
