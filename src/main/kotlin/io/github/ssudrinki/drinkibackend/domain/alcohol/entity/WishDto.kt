package io.github.ssudrinki.drinkibackend.domain.alcohol.entity

import kotlinx.datetime.LocalDateTime

/**
 * 술 위시를 나타내는 DTO 클래스
 *
 * 이 클래스는 Wishes 테이블의 결과를 리턴하기 위해 사용됩니다.
 */
data class WishDto(
        var id: Int,
        var userId: Long,
        var alcoholId: Int,
        var createdAt : LocalDateTime
)