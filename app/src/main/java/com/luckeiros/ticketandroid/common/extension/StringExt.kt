package com.luckeiros.ticketandroid.common.extension

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDate(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val formatter = SimpleDateFormat("MMM dd", Locale.US)
    val date = parser.parse(this) ?: return this
    return formatter.format(date)
}
