package com.luckeiros.ticketandroid.feature.event.domain

data class Event(
    val name: String,
    val date: String,
    val time: String?,
    val venueName: String,
    val city: String,
    val state: String,
    val imageUrl: String
)