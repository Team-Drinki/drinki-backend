package io.github.teamdrinki.drinkibackend.presentation.controller

import io.github.teamdrinki.drinkibackend.domain.alcohol.data.request.AlcoholSearchRequest
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholDetailResponse
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholListItem
import io.github.teamdrinki.drinkibackend.domain.alcohol.service.AlcoholService
import io.github.teamdrinki.drinkibackend.domain.alcohol.service.WishService
import io.github.teamdrinki.drinkibackend.domain.tastingnote.service.TastingNoteService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * 술 관련 API를 제공하는 컨트롤러
 *
 * 이 컨트롤러는 술 검색, 상세 조회, 위시리스트 관리 등의
 * REST API 엔드포인트를 제공합니다.
 */
@RestController
@RequestMapping("/api/v1/alcohols")
class AlcoholController(
        private val alcoholService: AlcoholService,
        private val wishService: WishService,
        private val tastingNoteService: TastingNoteService,
) {
    /**
     * 술 목록을 검색합니다.
     *
     * 다양한 조건(카테고리, 가격, 평점 등)으로 술을 검색할 수 있으며,
     * 페이징과 정렬을 지원합니다.
     *
     * @param request 검색 조건 (페이지, 크기, 정렬, 필터 등)
     * @return 검색된 술 목록과 페이징 정보
     *
     * @example GET /api/v1/alcohols/search?page=0&size=10&category=whiskey&priceMin=30000
     */
    @GetMapping("/search")
    fun getAlcoholList(
            @Valid request: AlcoholSearchRequest
    ): ResponseEntity<List<AlcoholListItem>> {
        val response = alcoholService.searchAlcohols(request)
        return ResponseEntity.ok(response)
    }

    /**
     * 특정 술의 상세 정보를 조회합니다.
     *
     * 술의 기본 정보, 평점, 리뷰, 추천 술 등의
     * 상세 페이지에 필요한 모든 정보를 반환합니다.
     *
     * @param id 조회할 술의 고유 식별자
     * @return 술의 상세 정보
     * @throws AlcoholNotFoundException 존재하지 않는 술 ID인 경우 404 반환
     *
     * @example GET /api/v1/alcohols/123
     */
    @GetMapping("/{id}")
    fun getAlcohol(
            @PathVariable id: Int
    ): ResponseEntity<AlcoholDetailResponse>{
        val userId = 1L

        val response = alcoholService.getAlcoholDetail(userId, id)
        return ResponseEntity.ok(response)
    }
}

