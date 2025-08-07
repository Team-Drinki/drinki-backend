package io.github.ssudrinki.drinkibackend.common.util

data class PageUtil (
    val currentPage: Int,
    val totalPages: Int,
    val totalCount: Long,
    val pageSize: Int,
    val hasNext: Boolean,
    val hasPrevious: Boolean
)