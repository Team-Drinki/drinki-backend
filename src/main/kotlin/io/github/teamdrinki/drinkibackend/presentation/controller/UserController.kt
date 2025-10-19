package io.github.teamdrinki.drinkibackend.presentation.controller

import io.github.teamdrinki.drinkibackend.domain.alcohol.data.request.WishListRequest
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholListResponse
import io.github.teamdrinki.drinkibackend.domain.alcohol.service.AlcoholService
import io.github.teamdrinki.drinkibackend.domain.user.data.request.UserProfileUpdateRequest
import io.github.teamdrinki.drinkibackend.domain.user.data.response.UserProfileResponse
import io.github.teamdrinki.drinkibackend.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {

    /**
     * 현재 사용자의 프로필 정보를 조회합니다.
     *
     * @return
     */
    @GetMapping("/profiles")
    fun getUserProfile(
        @AuthenticationPrincipal userId: Long
    ): ResponseEntity<UserProfileResponse>{
        val response = userService.getUserProfile(userId)
        return ResponseEntity.ok(response)
    }

    /**
     * 현재 사용자의 프로필 정보를 수정합니다.
     *
     * @return
     */
    @PostMapping("/profiles")
    fun updateUserProfile(
        @AuthenticationPrincipal userId: Long,
        @Valid @RequestBody request: UserProfileUpdateRequest
    ): ResponseEntity<Unit>{
        userService.updateUserProfile(userId, request)
        return ResponseEntity.status(HttpStatus.CREATED).build()    // 201 Created
    }

    /**
     * 현재 사용자의 위시리스트를 조회합니다
     *
     * 로그인한 사용자의 위시리스트를 조회합니다
     *
     * @param
     */
    @GetMapping("/")
    fun getWishList(
        @AuthenticationPrincipal userId: Long,
        @Valid @RequestBody request: WishListRequest
    ): ResponseEntity<AlcoholListResponse> {
        val response = userService.getUserWishList(userId, request)
        return ResponseEntity.ok(response)
    }

}