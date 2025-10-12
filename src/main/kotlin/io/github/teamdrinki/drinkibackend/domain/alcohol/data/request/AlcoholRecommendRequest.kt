package io.github.teamdrinki.drinkibackend.domain.alcohol.data.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern

/**
 * 술 추천 요청을 나타내는 데이터 클래스
 *
 * - 술 추천 요청에 필요한 조건
 * - 페이지네이션을 지원
 *
 * @property page 조회할 페이지 번호
 * @property size 페이지당 결과 수
 */
data class AlcoholRecommendRequest(
    @field:Min(value = 1, message = "페이지는 1 이상이어야 합니다")
    @field:Max(value = 1000, message = "페이지는 1000 이하여야 합니다")
    val page: Int = 1,

    @field:Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다")
    @field:Max(value = 100, message = "페이지 크기는 100 이하여야 합니다")
    val size: Int = 9,
) {}