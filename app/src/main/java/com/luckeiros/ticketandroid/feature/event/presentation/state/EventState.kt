package com.luckeiros.ticketandroid.feature.event.presentation.state

import com.luckeiros.ticketandroid.feature.event.domain.Event

sealed class EventState {
    data object Loading : EventState()
    data object Error : EventState()
    data class Success(val events: List<Event>, val firstLoad: Boolean) : EventState()
}