package com.luckeiros.ticketandroid.feature.event.domain

data class Events(
    val events: List<Event>,
    val page: Page
)

data class Event(
    val name: String,
    val date: String?,
    val venue: String?,
    val city: String?,
    val stateCode: String?,
    val images: List<EventImage>?
)

data class EventImage(
    val url: String,
    val ratio: String,
    val width: Int,
    val height: Int
)

data class Page(
    val size: Int,
    val totalElements: Int,
    val totalPages: Int,
    val number: Int
)
