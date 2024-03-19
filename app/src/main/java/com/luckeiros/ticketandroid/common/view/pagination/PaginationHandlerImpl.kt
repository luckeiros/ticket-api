package com.luckeiros.ticketandroid.common.view.pagination

import kotlin.Int.Companion.MAX_VALUE

class PaginationHandlerImpl : PaginationHandler {
    private val initialPage: Int = 0
    private val initialTotalPages: Int = MAX_VALUE

    override var currentPage = initialPage
        private set

    override var totalPages = initialTotalPages
        private set

    override val isLastPage = currentPage >= totalPages

    override fun incrementPage() {
        if (currentPage < totalPages) currentPage++
    }

    override fun reset() {
        currentPage = initialPage
        totalPages = initialTotalPages
    }

    override fun setTotalPages(total: Int) {
        totalPages = total
    }
}