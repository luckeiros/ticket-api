package com.luckeiros.ticketandroid.feature.event.helper

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also { observeForever(it) }