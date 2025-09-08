package io.github.teamdrinki.drinkibackend.presentation.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController {
    /**
     * 현재 인증된 사용자의 ID를 반환하는 API 엔드포인트입니다.
     *
     * @param auth 현재 인증된 사용자의 인증 정보
     * @return 인증된 사용자의 ID
     */
    @GetMapping("/me")
    fun me(auth: Authentication): ResponseEntity<Long> {
        val userId = auth.principal as Long
        println("userId = $userId")
        return ResponseEntity.ok()
            .body(userId)
    }
}