package com.luckeiros.ticketandroid.feature.event.data.remote.response

import com.google.gson.annotations.SerializedName

data class EventResponseDTO(
    @SerializedName("_embedded")
    val embedded: EmbeddedEventsDTO?
)

data class EmbeddedEventsDTO(
    @SerializedName("events")
    val events: List<EventDTO>?
)

data class EventDTO(
    @SerializedName("name")
    val name: String?,
    @SerializedName("dates")
    val dates: EventDatesDTO?,
    @SerializedName("_embedded")
    val embedded: EmbeddedVenuesDTO?,
    @SerializedName("images")
    val images: List<EventImageDTO>?
)

data class EventDatesDTO(
    @SerializedName("start")
    val start: EventStartDTO?
)

data class EventStartDTO(
    @SerializedName("localDate")
    val localDate: String?
)

data class EmbeddedVenuesDTO(
    @SerializedName("venues")
    val venues: List<VenueDTO>?
)

data class VenueDTO(
    @SerializedName("name")
    val name: String?,
    @SerializedName("city")
    val city: CityDTO?,
    @SerializedName("state")
    val state: StateDTO?
)

data class CityDTO(
    @SerializedName("name")
    val name: String?
)

data class StateDTO(
    @SerializedName("stateCode")
    val stateCode: String?
)

data class EventImageDTO(
    @SerializedName("url")
    val url: String?
)

data class PageDTO(
    @SerializedName("size")
    val size: Int?,
    @SerializedName("totalElements")
    val totalElements: Int?,
    @SerializedName("totalPages")
    val totalPages: Int?,
    @SerializedName("number")
    val number: Int?
)