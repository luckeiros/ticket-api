package com.luckeiros.ticketandroid.core.network

import java.io.IOException

class NoConnectionException(cause: Throwable? = null) : IOException(
    "No connection", cause
)

class BusinessException(cause: Throwable? = null) : IOException(
    "Business error", cause
)

class TimeoutException(cause: Throwable? = null) : IOException(
    "Time out", cause
)

class UnknownException(cause: Throwable? = null) : IOException(
    "Unknown error", cause
)