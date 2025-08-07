package io.github.ssudrinki.drinkibackend.domain.alcohol.dto.request

import jakarta.validation.constraints.*

/**
 * 술 검색 요청을 나타내는 데이터 클래스
 *
 * 이 클래스는 사용자의 위스키 검색 조건을 담고 있으며,
 * 페이지네이션, 필터링, 정렬 기능을 지원합니다.
 *
 * @property page 조회할 페이지 번호 (1부터 시작)
 * @property size 페이지당 결과 수 (최대 100개)
 * @property sort 정렬 방식 (CreatedAt, Name, Rating, Like, Price, PriceDesc 중 하나)
 * @property query 검색할 키워드
 * @property category 카테고리 필터
 * @property location 지역 필터
 * @property style 제조 스타일 필터
 * @property priceMin 최소 가격 (0 이상)
 * @property priceMax 최대 가격 (기본값: 10억)
 * @property rating 최소 평점 (0.0 ~ 5.0)
 */
data class AlcoholSearchRequest(
        @field:Min(value = 1, message = "페이지는 1 이상이어야 합니다")
        @field:Max(value = 1000, message = "페이지는 1000 이하여야 합니다")
        val page: Int = 1,

        @field:Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다")
        @field:Max(value = 100, message = "페이지 크기는 100 이하여야 합니다")
        val size: Int = 10,

        @field:Pattern(
                regexp = "^(CreatedAt|View|TastingNote|Like|Rating|PriceDesc|PriceAsc)$",
                message = "정렬 방식은 CreatedAt, View, TastingNote, Like, Rating, PriceDesc, PriceAsc 중 하나여야 합니다"
        )
        val sort: String = "CreatedAt",

        @field:Size(max = 100, message = "검색어는 100자 이하여야 합니다")
        val query: String = "",

        @field:Size(max = 50, message = "카테고리는 50자 이하여야 합니다")
        val category: String = "",

        @field:Size(max = 50, message = "지역은 50자 이하여야 합니다")
        val location: String = "",

        @field:Size(max = 50, message = "스타일은 50자 이하여야 합니다")
        val style: String = "",

        @field:Max(value = 1000000000, message = "최대 가격은 10억 이하여야 합니다")
        @field:Min(value = 0, message = "최소 가격은 0 이상이어야 합니다")
        val priceMax: Int = 1000000000,

        @field:Max(value = 1000000000, message = "최대 가격은 10억 이하여야 합니다")
        @field:Min(value = 0, message = "최소 가격은 0 이상이어야 합니다")
        val priceMin: Int = 0,

        @field:DecimalMin(value = "0.0", message = "평점은 0.0 이상이어야 합니다")
        @field:DecimalMax(value = "5.0", message = "평점은 5.0 이하여야 합니다")
        val rating: Double = 0.0
) {
    /**
     * 가격 필터가 적용되었는지 확인합니다.
     *
     * @return 최소 가격이 0보다 크거나 최대 가격이 기본값보다 작으면 true
     */
    fun hasPriceFilter(): Boolean {
        return priceMin > 0 || priceMax < 1000000000
    }
}