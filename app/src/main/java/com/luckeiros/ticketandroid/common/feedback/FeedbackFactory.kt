package com.luckeiros.ticketandroid.common.feedback

import com.luckeiros.ticketandroid.core.network.NoConnectionException
import com.luckeiros.ticketandroid.core.network.TimeoutException

object FeedbackFactory {
    private const val GENERIC_ERROR_MESSAGE = "Unexpected error: please try again later."

    fun create(error: Exception): Feedback {
        val message = when (error) {
            is NoConnectionException -> "No connection: check your internet and try again."
            is TimeoutException -> "Slow connection: it's taking longer than expected. Please try again later."
            else -> GENERIC_ERROR_MESSAGE
        }

        return Feedback(message = message)
    }
}
