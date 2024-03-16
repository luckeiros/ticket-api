package com.luckeiros.ticketandroid.feature.event.data.mapper

import com.luckeiros.ticketandroid.feature.event.data.remote.response.CityDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EmbeddedVenuesDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventDatesDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventImageDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventResponseDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.EventStartDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.StateDTO
import com.luckeiros.ticketandroid.feature.event.data.remote.response.VenueDTO
import com.luckeiros.ticketandroid.feature.event.domain.City
import com.luckeiros.ticketandroid.feature.event.domain.EmbeddedVenues
import com.luckeiros.ticketandroid.feature.event.domain.Event
import com.luckeiros.ticketandroid.feature.event.domain.EventDates
import com.luckeiros.ticketandroid.feature.event.domain.EventImage
import com.luckeiros.ticketandroid.feature.event.domain.EventStart
import com.luckeiros.ticketandroid.feature.event.domain.State
import com.luckeiros.ticketandroid.feature.event.domain.Venue

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
    localDate = localDate.orEmpty(),
    dateTime = dateTime.orEmpty()
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
    url = url.orEmpty()
)