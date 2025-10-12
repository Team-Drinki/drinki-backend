package io.github.teamdrinki.drinkibackend.common.dto

data class PagedListResult<T>(
    val content: List<T>,
    val totalCnt: Long
)