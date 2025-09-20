package io.github.teamdrinki.drinkibackend.domain.alcohol.data.dto

/**
 * 술 상세 정보 조회를 위한 DTO
 *
 * 술 상세 정보에
 * 이 클래스는 Service Layer 에서 Controller Layer로 전달하는 역할을 합니다.
 */
data class AlcoholDetailDto {
    val id: Int,
    val name: String,
    val proof: Float,
    val image: String?,
    val category: String,
    val location: String,
    val style: String,
    val description: String,
    val wish: Int,
    val rating: Float,
    val isWished: Boolean,
}