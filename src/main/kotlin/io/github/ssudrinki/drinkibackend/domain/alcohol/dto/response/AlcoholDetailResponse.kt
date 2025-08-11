package io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response

/**
 * 술 정보를 나타내는 응답
 *
 * 이 클래스는 술 정보 API의 응답 데이터를 담고 있으며,
 * 술 정보를 클라이언트에게 전달하는 역할을 합니다.
 *
 * @property id 술의 고유 식별자. 상세 조회 시 사용되는 기본키
 * @property name 술의 이름. 사용자에게 표시되는 주요 정보
 * @property proof 술의 도수(알코올 함량). 0.0 이상의 실수 값
 * @property image 술 이미지 URL. null인 경우 기본 이미지 사용
 * @property category 술의 카테고리. 예: "싱글몰트", "블렌디드", "버번" 등
 * @property location 술의 원산지/생산지. 예: "스코틀랜드", "아일랜드", "미국" 등
 * @property style 술의 스타일/종류. 카테고리보다 세분화된 분류
 * @property description 술에 대한 상세 설명. 맛, 향, 제조 과정 등의 정보
 * @property wish 위시(좋아요) 수. 사용자들의 선호도를 나타내는 지표
 * @property rating 평균 평점. 0.0 ~ 5.0 범위의 소수점 값
 * @property isWished 현재 사용자의 위시 여부. true인 경우 이미 위시한 상태
 * @property bestNotes 베스트 테이스팅 노트 목록. 평점이 높거나 인기 있는 노트들
 * @property recommendAlcohols 추천 술 목록. 현재 술과 유사하거나 관련된 추천 술들
 */
data class AlcoholDetailResponse (
    val id: Int,
    val name: String,
    val proof: Double,
    val image: String?,
    val category: String,
    val location: String,
    val style: String,
    val description: String,
    val wish: Int,
    val rating: Double,
    val isWished: Boolean,

    val bestNotes: List<TastingNotesListItem>,
    val recommendAlcohols: List<AlcoholListItem>
)