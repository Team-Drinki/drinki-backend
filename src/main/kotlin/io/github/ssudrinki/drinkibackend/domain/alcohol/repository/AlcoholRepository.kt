package io.github.ssudrinki.drinkibackend.domain.alcohol.repository

import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholListItem

interface AlcoholRepository {

    /**
     * 검색 조건에 맞는 술 목록을 조회합니다.
     *
     * @param query 검색 키워드
     * @param page 페이지 번호 (1부터 시작)
     * @param size 페이지 크기
     * @param sort 정렬 방식
     * @param category 카테고리 필터
     * @param location 지역 필터
     * @param style 스타일 필터
     * @param priceMin 최소 가격
     * @param priceMax 최대 가격
     * @param rating 최소 평점
     * @return 검색된 술 목록
     */
    fun searchAlcohols(query: String, page: Int, size: Int, sort: String,
                       category: String, location: String, style: String,
                       priceMin: Int, priceMax: Int, rating: Double // AlcoholSearchRequest
    ): List<AlcoholListItem>
//    fun findById(id: Int): ResultRow?
//    fun create(name: String, proof: Short, categoryId: Int, styleId: Int, locationId: Int,
//               content: String?, price: BigDecimal?, imageUrl: String?): Int
//    fun update(id: Int, name: String?, proof: Short?, content: String?): Boolean
//    fun delete(id: Int): Boolean
//    fun getTotalCount(query: String): Long
}