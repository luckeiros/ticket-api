package com.luckeiros.ticketandroid.feature.event.domain.mapper

import com.luckeiros.ticketandroid.common.extension.orZero
import com.luckeiros.ticketandroid.feature.event.data.remote.response.CityDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EmbeddedVenuesDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventDatesDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventImageDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventResponseDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventStartDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.PageDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.StateDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.VenueDTO
import com.luckeiros.ticketandroid.feature.event.domain.City
import com.luckeiros.ticketandroid.feature.event.domain.EmbeddedVenues
import com.luckeiros.ticketandroid.feature.event.domain.Event
import com.luckeiros.ticketandroid.feature.event.domain.EventDates
import com.luckeiros.ticketandroid.feature.event.domain.EventImage
import com.luckeiros.ticketandroid.feature.event.domain.EventStart
import com.luckeiros.ticketandroid.feature.event.domain.Page
import com.luckeiros.ticketandroid.feature.event.domain.State
import com.luckeiros.ticketandroid.feature.event.domain.Venue
import com.luckeiros.ticketandroid.feature.event.presentation.view.EventItem

fun EventResponseDTO.toEventList(): List<Event> =
    embedded?.events?.map { eventDTO ->
        eventDTO.toModel()
    } ?: emptyList()

private fun EventDTO.toModel() = Event(
    name = name.orEmpty(),
    dates = dates?.toModel(),
    embedded = embedded?.toModel(),
    images = images?.map { it.toModel() } ?: emptyList()
)

private fun EventDatesDTO.toModel() = EventDates(
    start = start?.toModel()
)

private fun EventStartDTO.toModel() = EventStart(
    date = localDate.orEmpty()
)

private fun EmbeddedVenuesDTO.toModel() = EmbeddedVenues(
    venues = venues?.map { it.toModel() } ?: emptyList()
)

private fun VenueDTO.toModel() = Venue(
    name = name.orEmpty(),
    city = city?.toModel(),
    state = state?.toModel()
)

private fun CityDTO.toModel() = City(
    name = name.orEmpty()
)

private fun StateDTO.toModel() = State(
    stateCode = stateCode.orEmpty()
)

private fun EventImageDTO.toModel() = EventImage(
    url = url.orEmpty(),
    ratio = ratio.orEmpty(),
    width = width.orZero(),
    height = height.orZero()
)

private fun PageDTO.toModel() = Page(
    size = size.orZero(),
    totalElements = totalElements.orZero(),
    totalPages = totalPages.orZero(),
    number = number.orZero()
)

fun List<Event>.toEventItem(): List<EventItem> = this.map { event ->
    val venue = event.embedded?.venues?.firstOrNull()
    val city = event.embedded?.venues?.firstOrNull()?.city?.name.orEmpty()
    val stateCode = event.embedded?.venues?.firstOrNull()?.state?.stateCode.orEmpty()
    val location = if (stateCode.isNotEmpty()) "$city, $stateCode" else city

    EventItem(
        name = event.name,
        date = event.dates?.start?.date.orEmpty(),
        venue = venue?.name.orEmpty(),
        location = location,
        image = event.images
    )
}
