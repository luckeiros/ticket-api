package com.luckeiros.ticketandroid.feature.event.domain

data class Event(
    val name: String,
    val dates: EventDates?,
    val embedded: EmbeddedVenues?,
    val images: List<EventImage>
)

data class EventDates(
    val start: EventStart?
)

data class EventStart(
    val localDate: String,
    val dateTime: String
)

data class EmbeddedVenues(
    val venues: List<Venue>
)

data class Venue(
    val name: String,
    val city: City?,
    val state: State?
)

data class City(
    val name: String
)

data class State(
    val stateCode: String
)

data class EventImage(
    val url: String
)