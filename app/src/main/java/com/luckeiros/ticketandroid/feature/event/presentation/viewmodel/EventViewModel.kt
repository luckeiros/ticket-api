package com.luckeiros.ticketandroid.feature.event.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luckeiros.ticketandroid.common.extension.emit
import com.luckeiros.ticketandroid.common.extension.safeLaunch
import com.luckeiros.ticketandroid.feature.event.data.repository.EventRepository
import com.luckeiros.ticketandroid.feature.event.presentation.state.EventState

internal class EventViewModel(private val repository: EventRepository) : ViewModel() {

    private val mutableState = MutableLiveData<EventState>()
    val state: LiveData<EventState> = mutableState

    private suspend fun getEvents(city: String) {
        val event = repository.getEvents(city)
        mutableState.emit(EventState.Success(event))
    }

    fun loadEvents(city: String) = safeLaunch(::handleError) {
        mutableState.emit(EventState.Loading)
        getEvents(city)
    }

    private fun handleError(error: Throwable) {
        mutableState.emit(EventState.Error)
    }
}