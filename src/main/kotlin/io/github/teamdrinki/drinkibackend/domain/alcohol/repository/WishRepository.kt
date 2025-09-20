package io.github.teamdrinki.drinkibackend.domain.alcohol.repository

import io.github.ssudrinki.drinkibackend.domain.alcohol.data.dao.WishDto

/**
 * 술 위시 정보를 관리하는 Repository 인터페이스
 *
 * 이 인터페이스는 술 위시에 대한 CRUD 작업과
 * 검색, 필터링 기능을 제공합니다.
 */
interface WishRepository {
    /**
     * 사용자의 술 위시 리스트를 조회합니다
     *
     * @param userId         위시리스트 조회할 사용자 ID
     * @return 위시 리스트
     */
    fun findByUserId(userId: Long): List<WishDto>

    /**
     * 사용자의 특정 술 위시 정보를 조회합니다.
     *
     * @param userId    사용자 ID
     * @param alcoholId 위시 조회할 술 ID
     * @return 특정 술의 위시
     */
    fun findByUserIdAndAlcoholId(userId: Long, alcoholId: Int): WishDto?

    /**
     * 사용자의 술 위시를 추가합니다.
     *
     * @param userId 사용자 ID
     * @param alcoholId 추가할 술 ID
     */
    fun create(userId: Long, alcoholId: Int)

    /**
     * 사용자의 술 위시를 삭제합니다.
     *
     * @param userId 사용자 ID
     * @param alcoholId 삭제할 술 ID
     */
    fun delete(userId: Long, alcoholId: Int)
}