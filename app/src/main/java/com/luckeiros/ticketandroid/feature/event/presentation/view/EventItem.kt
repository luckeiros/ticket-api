package com.luckeiros.ticketandroid.feature.event.presentation.view

import com.luckeiros.ticketandroid.feature.event.domain.EventImage

data class EventItem(
    val name: String,
    val date: String,
    val venue: String,
    val location: String,
    val image: List<EventImage>
)