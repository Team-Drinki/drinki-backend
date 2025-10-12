package io.github.teamdrinki.drinkibackend.common.util

import kotlin.math.ceil

data class PageUtil (
    val currentPage: Int,
    val totalPages: Int,
    val totalCount: Long,
    val pageSize: Int,
    val hasNext: Boolean,
    val hasPrevious: Boolean
) {
    companion object {
        /**
         * 페이징 정보를 생성합니다.
         *
         * @param page 현재 페이지 (1부터 시작)
         * @param size 페이지 크기
         * @param totalCount 전체 데이터 개수
         * @return PageUtil 인스턴스
         */
        fun of(page: Int, size: Int, totalCount: Long): PageUtil {
            val totalPages = if (totalCount == 0L) 0 else ceil(totalCount.toDouble() / size).toInt()

            return PageUtil(
                currentPage = page,
                totalPages = totalPages,
                totalCount = totalCount,
                pageSize = size,
                hasNext = page < totalPages,
                hasPrevious = page > 1
            )
        }
    }
}