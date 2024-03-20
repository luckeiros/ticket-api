package com.luckeiros.ticketandroid.feature.event.presentation.state

import com.luckeiros.ticketandroid.common.feedback.Feedback
import com.luckeiros.ticketandroid.feature.event.domain.Event

sealed class EventState {
    data object Loading : EventState()
    data class Error(val feedback: Feedback) : EventState()
    data class Success(val events: List<Event>, val firstLoad: Boolean) : EventState()
    data object NoEventsFound : EventState()
}