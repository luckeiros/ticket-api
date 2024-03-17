package com.luckeiros.ticketandroid.feature.event.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luckeiros.ticketandroid.common.extension.emit
import com.luckeiros.ticketandroid.common.extension.safeLaunch
import com.luckeiros.ticketandroid.feature.event.data.repository.EventRepository
import com.luckeiros.ticketandroid.feature.event.domain.mapper.toEventItem
import com.luckeiros.ticketandroid.feature.event.presentation.state.EventState

internal class EventViewModel(private val repository: EventRepository) : ViewModel() {

    private val mutableState = MutableLiveData<EventState>()
    val state: LiveData<EventState> = mutableState

    private suspend fun getEvent(city: String) {
        val event = repository.getEvent(city)
        mutableState.emit(EventState.Success(event.toEventItem()))
    }

    fun loadEvent(city: String) = safeLaunch(::handleError) {
        mutableState.emit(EventState.Loading)
        getEvent(city)
    }

    private fun handleError(error: Throwable) {
        mutableState.emit(EventState.Error)
    }
}