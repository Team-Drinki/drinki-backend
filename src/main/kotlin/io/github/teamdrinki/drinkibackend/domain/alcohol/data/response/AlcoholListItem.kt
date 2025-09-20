package io.github.teamdrinki.drinkibackend.domain.alcohol.data.response

/**
 * 술 목록에서 개별 술 간단 정보를 나타내는 데이터 클래스
 *
 * 이 클래스는 술 목록 화면에서 표시할 핵심 정보들을 담고 있습니다.
 *
 * @property id 술의 고유 식별자. 상세 조회 시 사용
 * @property name 술의 이름. 사용자에게 표시되는 주요 정보
 * @property image 술 이미지 URL. null인 경우 기본 이미지 사용
 * @property category 술의 카테고리. 예: "싱글몰트", "블렌디드", "버번" 등
 * @property like 위시(좋아요) 수. 사용자들의 선호도를 나타내는 지표
 * @property rating 평균 평점. 0.0 ~ 5.0 범위의 소수점 값
 * @property viewCnt 조회수. 술의 인기도를 나타내는 지표
 * @property noteCnt 테이스팅 노트 개수. 사용자 참여도를 나타내는 지표
 */
data class AlcoholListItem(
        val id: Int,
        val name: String,
        val image: String?,
        val category: String,
        val like: Int,
        val rating: Double,
        val viewCnt: Int,
        val noteCnt: Int
)