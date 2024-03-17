package com.luckeiros.ticketandroid.feature.event.presentation.state

import com.luckeiros.ticketandroid.feature.event.presentation.view.EventItem

sealed class EventState {
    data object Loading : EventState()
    data object Error : EventState()
    data class Success(val events: List<EventItem>) : EventState()
}