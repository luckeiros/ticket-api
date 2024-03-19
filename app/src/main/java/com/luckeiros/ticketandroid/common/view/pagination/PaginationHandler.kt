package com.luckeiros.ticketandroid.common.view.pagination

interface PaginationHandler {
    val currentPage: Int
    val totalPages: Int
    val isLastPage: Boolean
    fun incrementPage()
    fun reset()
    fun setTotalPages(total: Int)
}