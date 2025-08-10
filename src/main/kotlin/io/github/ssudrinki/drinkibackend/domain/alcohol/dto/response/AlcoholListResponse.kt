package io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response

import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.request.AlcoholSearchRequest

/**
 * 술 목록 검색 결과를 나타내는 응답 클래스
 *
 * 이 클래스는 술 검색 API의 응답 데이터를 담고 있으며,
 * 검색된 술 목록을 클라이언트에게 전달하는 역할을 합니다.
 *
 * @property alcohols 검색된 술 목록. 빈 리스트일 수 있음
 */
data class AlcoholListResponse(
        val alcohols: List<AlcoholListItem>
)