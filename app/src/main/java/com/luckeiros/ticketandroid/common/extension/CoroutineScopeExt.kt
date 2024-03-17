package com.luckeiros.ticketandroid.common.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun ViewModel.safeLaunch(
    onError: (error: Exception) -> Unit,
    block: suspend CoroutineScope.() -> Unit
) = viewModelScope.safeLaunch(block, onError)

fun CoroutineScope.safeLaunch(
    block: suspend CoroutineScope.() -> Unit,
    onError: (error: Exception) -> Unit
) {
    launch {
        try {
            block.invoke(this)
        } catch (error: Exception) {
            onError.invoke(error)
        }
    }
}