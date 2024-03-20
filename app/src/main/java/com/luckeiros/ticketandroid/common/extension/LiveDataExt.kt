package com.luckeiros.ticketandroid.common.extension

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@MainThread
fun <T> LiveData<T>.emit(value: T) {
    require(this is MutableLiveData) { "$this isn't a valid MutableLiveData instance" }

    this.value = value
}