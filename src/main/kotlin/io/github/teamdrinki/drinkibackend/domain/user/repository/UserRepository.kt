package io.github.teamdrinki.drinkibackend.domain.user.repository

import io.github.teamdrinki.drinkibackend.schema.UserEntity

/**
 * 사용자 정보를 관리하는 Repository 인터페이스
 */
interface UserRepository {

    /**
     * ID로 사용자를 조회합니다.
     *
     * @param userId 사용자 ID
     * @return User
     */
    fun findById(userId: Long): UserEntity

    /**
     * ID로 사용자 정보를 수정합니다.
     *
     * @param userId 사용자 ID
     * @param update 수정할 내용
     * @return User
     *
     */
    fun updateById(userId: Long, update: (UserEntity) -> Unit): UserEntity?

}