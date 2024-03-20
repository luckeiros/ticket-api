package com.luckeiros.ticketandroid.feature.event.helper

import com.luckeiros.ticketandroid.feature.event.domain.Event
import com.luckeiros.ticketandroid.feature.event.domain.EventImage
import com.luckeiros.ticketandroid.feature.event.domain.Events
import com.luckeiros.ticketandroid.feature.event.domain.Page

object EventTestHelper {
    private val mockEventImage = EventImage(
        url = "https://example.com/image.jpg",
        ratio = "16:9",
        width = 1920,
        height = 1080
    )

    private val mockEvent = Event(
        name = "Sample Event",
        date = "2022-01-01",
        venue = "Sample Venue",
        city = "Sample City",
        stateCode = "SC",
        images = listOf(mockEventImage)
    )

    private val mockPage = Page(totalPages = 2)

    val mockEvents = Events(
        events = listOf(mockEvent),
        page = mockPage
    )

    val emptyEvents = Events(
        events = emptyList(),
        page = mockPage
    )
}