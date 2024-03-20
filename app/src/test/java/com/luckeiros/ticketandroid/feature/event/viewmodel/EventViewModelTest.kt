package com.luckeiros.ticketandroid.feature.event.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luckeiros.ticketandroid.common.extension.emit
import com.luckeiros.ticketandroid.common.view.pagination.PaginationHandler
import com.luckeiros.ticketandroid.feature.event.data.repository.EventRepository
import com.luckeiros.ticketandroid.feature.event.helper.EventTestHelper.emptyEvents
import com.luckeiros.ticketandroid.feature.event.helper.EventTestHelper.mockEvents
import com.luckeiros.ticketandroid.feature.event.helper.testObserver
import com.luckeiros.ticketandroid.feature.event.presentation.state.EventState
import com.luckeiros.ticketandroid.feature.event.presentation.viewmodel.EventViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EventViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val mockRepository: EventRepository = mockk(relaxed = true)
    private val mockPagination: PaginationHandler = mockk(relaxed = true)

    private lateinit var viewModel: EventViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = EventViewModel(mockRepository, mockPagination)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getEvents should emit Loading and Success states`() = runTest {
        coEvery { mockRepository.getEvents(any(), any()) } returns mockEvents

        val stateObserver = viewModel.state.testObserver()

        viewModel.getEvents()

        advanceUntilIdle()

        assertTrue(stateObserver.observedValues.contains(EventState.Loading))
        assertTrue(stateObserver.observedValues.any {
            it is EventState.Success && it.events == mockEvents.events
        })

        viewModel.state.removeObserver(stateObserver)
    }


    @Test
    fun `getMoreEvents should emit PaginationLoading and Success states when theres more events available`() =
        runTest {
            every { mockPagination.isLastPage } returns false
            every { mockPagination.currentPage } returns FIRST_PAGE_INDEX

            coEvery { mockRepository.getEvents(any(), 1) } returns mockEvents

            val stateObserver = viewModel.state.testObserver()
            val paginationLoadingObserver = viewModel.paginationLoading.testObserver()

            viewModel.getMoreEvents()

            advanceUntilIdle()

            assertTrue(paginationLoadingObserver.observedValues.first() == true)
            assertTrue(paginationLoadingObserver.observedValues.last() == false)
            assertTrue(stateObserver.observedValues.any {
                it is EventState.Success && it.events == mockEvents.events
            })

            coVerify { mockRepository.getEvents(null, 1) }
        }

    @Test
    fun `getMoreEvents should do nothing when pagination is loading`() = runTest {
        viewModel.paginationLoading.emit(true)

        viewModel.getMoreEvents()

        coVerify(exactly = 0) { mockRepository.getEvents(any(), any()) }
    }

    @Test
    fun `getMoreEvents should do nothing when last page is reached`() = runTest {
        every { mockPagination.isLastPage } returns true

        viewModel.getMoreEvents()

        coVerify(exactly = 0) { mockRepository.getEvents(any(), any()) }
    }

    @Test
    fun `getEvents should emit Error state on request error during initial load`() = runTest {
        val exception = RuntimeException()
        coEvery { mockRepository.getEvents(any(), 0) } throws exception

        val stateObserver = viewModel.state.testObserver()

        viewModel.getEvents()

        advanceUntilIdle()

        assertTrue(stateObserver.observedValues.any {
            it is EventState.Error && it.feedback.message == GENERIC_ERROR_MESSAGE
        })
    }

    @Test
    fun `getMoreEvents should emit Error state on request error during pagination load`() = runTest {
        every { mockPagination.isLastPage } returns false
        every { mockPagination.currentPage } returns 1

        val exception = RuntimeException()
        coEvery { mockRepository.getEvents(any(), 2) } throws exception

        val stateObserver = viewModel.state.testObserver()

        viewModel.getMoreEvents()

        advanceUntilIdle()

        assertTrue(stateObserver.observedValues.any {
            it is EventState.Error && it.feedback.message == GENERIC_ERROR_MESSAGE
        })
    }

    @Test
    fun `getEvents emits NoEventsFound state when no events are returned`() = runTest {
        coEvery { mockRepository.getEvents(any(), FIRST_PAGE_INDEX) } returns emptyEvents

        val stateObserver = viewModel.state.testObserver()

        viewModel.getEvents()

        advanceUntilIdle()

        assertTrue(stateObserver.observedValues.any { it is EventState.NoEventsFound })
    }

    private companion object {
        const val FIRST_PAGE_INDEX = 0
        const val GENERIC_ERROR_MESSAGE = "Unexpected error: please try again later."
    }
}