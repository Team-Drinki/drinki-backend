package io.github.teamdrinki.drinkibackend.domain.user.service

import io.github.teamdrinki.drinkibackend.domain.alcohol.data.request.WishListRequest
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholListResponse
import io.github.teamdrinki.drinkibackend.domain.user.data.request.UserProfileUpdateRequest
import io.github.teamdrinki.drinkibackend.domain.user.data.response.UserProfileResponse
import org.springframework.http.ResponseEntity

interface UserService {

    /**
     * 사용자의 프로필을 조회합니다.
     *
     * @param userId 유저 ID
     * @return 유저 프로필
     */
    fun getUserProfile(userId: Long): UserProfileResponse

    /**
     * 사용자의 프로필을 수정합니다.
     *
     * @param userId 유저 ID
     * @param request 유저 프로필 수정 요청
     */
    fun updateUserProfile(userId: Long, request: UserProfileUpdateRequest)

    /**
     * 사용자의 위시리스트를 조회합니다.
     *
     * @param userId 유저 ID
     * @param
     * @return 위시들의 리스트
     */
    fun getUserWishList(userId: Long, request: WishListRequest): AlcoholListResponse
}