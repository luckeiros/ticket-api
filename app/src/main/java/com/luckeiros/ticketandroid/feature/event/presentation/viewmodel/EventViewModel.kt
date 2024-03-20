package com.luckeiros.ticketandroid.feature.event.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luckeiros.ticketandroid.common.extension.emit
import com.luckeiros.ticketandroid.common.extension.safeLaunch
import com.luckeiros.ticketandroid.common.feedback.FeedbackFactory
import com.luckeiros.ticketandroid.common.view.pagination.PaginationHandler
import com.luckeiros.ticketandroid.feature.event.data.repository.EventRepository
import com.luckeiros.ticketandroid.feature.event.domain.Events
import com.luckeiros.ticketandroid.feature.event.domain.Page
import com.luckeiros.ticketandroid.feature.event.presentation.state.EventState

internal class EventViewModel(
    private val repository: EventRepository,
    private val pagination: PaginationHandler
) : ViewModel() {

    private val _state = MutableLiveData<EventState>()
    val state: LiveData<EventState> = _state

    private val _paginationLoading = MutableLiveData<Boolean>()
    val paginationLoading: LiveData<Boolean> = _paginationLoading

    private val isNotLoadingPagination: Boolean
        get() = _paginationLoading.value != true

    fun getEvents(city: String? = null) {
        loadData(city, isFirstLoad = true)
    }

    fun getMoreEvents(city: String? = null) {
        if (!shouldLoadMore()) return
        loadData(city, isFirstLoad = false)
    }

    private fun shouldLoadMore() = !pagination.isLastPage && isNotLoadingPagination

    private fun loadData(city: String?, isFirstLoad: Boolean) = safeLaunch(::handleError) {
        emitLoadingState(isFirstLoad)
        emitPaginationLoadingState(isFirstLoad)

        val page = if (isFirstLoad) FIRST_PAGE_INDEX else pagination.currentPage + INCREMENT_VALUE
        val response = repository.getEvents(city, page)

        handleResponse(response, isFirstLoad)

        _paginationLoading.emit(false)
    }

    private fun emitLoadingState(isFirstLoad: Boolean) {
        if (isFirstLoad) _state.value = EventState.Loading
    }

    private fun emitPaginationLoadingState(isFirstLoad: Boolean) {
        _paginationLoading.emit(!isFirstLoad)
    }

    private fun handleResponse(response: Events, isFirstLoad: Boolean) {
        pagination.updatePaginationInfo(response.page)

        if (response.events.isEmpty()) {
            _state.emit(EventState.NoEventsFound)
        } else {
            _state.emit(EventState.Success(response.events, isFirstLoad))
        }
    }

    private fun handleError(e: Exception) {
        val feedback = FeedbackFactory.create(e)

        _state.emit(EventState.Error(feedback))
        _paginationLoading.emit(false)
    }

    private fun PaginationHandler.updatePaginationInfo(page: Page) {
        setTotalPages(page.totalPages)
        if (currentPage < totalPages) incrementPage()
    }

    private companion object {
        const val FIRST_PAGE_INDEX = 0
        const val INCREMENT_VALUE = 1
    }
}
