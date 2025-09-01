package io.github.ssudrinki.drinkibackend.domain.alcohol.service

import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.request.AlcoholSearchRequest
import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholDetailResponse
import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholListResponse

/**
 * 술 관련 비즈니스 로직을 처리하는 서비스
 *
 * 이 서비스는 술 정보 관리, 검색, 위시리스트 관리 등의
 * 핵심 비즈니스 로직을 담당합니다.
 */
interface AlcoholService {

    /**
     * 지정된 조건에 따라 술 목록을 검색합니다.
     *
     * 검색 조건, 필터링, 정렬, 페이징을 적용하여
     * 사용자가 원하는 술 목록을 반환합니다.
     * 각 술의 기본 정보와 통계 정보(좋아요 수, 평점 등)를 포함합니다.
     *
     * @param alcoholSearchRequest 검색 조건 및 페이징 정보
     * @return 검색 조건에 맞는 술 목록
     */
    fun searchAlcohols(alcoholSearchRequest: AlcoholSearchRequest): AlcoholListResponse

    /**
     * 특정 술의 상세 정보를 조회합니다.
     *
     * 술의 기본 정보뿐만 아니라 관련된 카테고리, 위치, 스타일 정보를 조회하고,
     * 위시 통계, 평균 평점, 베스트 테이스팅 노트, 추천 술 목록을 계산합니다.
     *
     * @param alcoholId 조회할 술의 고유 식별자
     * @return 술의 종합적인 상세 정보
     * @throws AlcoholNotFoundException 존재하지 않는 술인 경우
     */
    fun getAlcoholDetail(alcoholId: Int): AlcoholDetailResponse





//    fun getAlcoholById(id: Int): AlcoholDetailResponse?                                     //
//    fun createAlcohol(request: AlcoholCreateRequest): Int                                   // 새로운 술 등록
//    fun updateAlcohol(id: Int, name: String?, proof: Short?, content: String?): Boolean     // 술 수정
//    fun deleteAlcohol(id: Int): Boolean                                                     // 술 삭제
}