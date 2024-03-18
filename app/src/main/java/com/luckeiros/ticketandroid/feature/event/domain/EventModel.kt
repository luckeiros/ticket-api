package com.luckeiros.ticketandroid.feature.event.domain

data class Event(
    val name: String,
    val date: String?,
    val venue: String?,
    val location: String?,
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
    val totalPages: Int,
    val number: Int
)
