package io.github.ssudrinki.drinkibackend.domain.alcohol.repository

/**
 * 술 위시 정보를 관리하는 Repository 인터페이스
 *
 * 이 인터페이스는 술 위시에 대한 CRUD 작업과
 * 검색, 필터링 기능을 제공합니다.
 */
interface WishRepository {

    fun findByUserId()

    fun findByUserIdAndAlcoholId(userId: Int, alcoholId: Int)

    fun create()

    fun delete()


}