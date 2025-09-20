package io.github.teamdrinki.drinkibackend.domain.alcohol.data.dao

import io.github.ssudrinki.drinkibackend.domain.tastingnote.dao.TastingNoteDao
import kotlinx.datetime.LocalDateTime
import java.math.BigDecimal

/**
 * 술 정보를 나타내는 DAO 클래스
 *
 * 이 클래스는 Alcohols 테이블의 결과를 리턴하기 위해 사용됩니다.
 */
data class AlcoholDao(
        val id: Int,
        val name: String,
        val imageUrl: String?,
        val price: BigDecimal,
        val proof: Float,
        val rating: BigDecimal?,
        val wish: Int,
        val content: String?,
        val category: String,
        val style: String,
        val location: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)
