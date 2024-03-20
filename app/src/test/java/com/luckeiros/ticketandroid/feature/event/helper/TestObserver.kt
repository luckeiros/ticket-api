package com.luckeiros.ticketandroid.feature.event.helper

import androidx.lifecycle.Observer

class TestObserver<T> : Observer<T> {
    val observedValues = mutableListOf<T?>()

    override fun onChanged(value: T) {
        observedValues.add(value)
    }
}