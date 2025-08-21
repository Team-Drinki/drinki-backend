package io.github.teamdrinki.drinkibackend.presentation.controller

import io.github.teamdrinki.drinkibackend.domain.auth.service.AuthService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController (private val authService: AuthService) {
    /**
     * 구글 로그인 API
     */
    @PostMapping("/login/google")
    fun loginGoogle(): String {
        return "redirect:/oauth2/authorization/google"
    }

    /**
     * 리프레시 토큰을 사용하여 새로운 액세스 토큰을 발급하는 API
     */
    @PostMapping("/refresh")
    fun refresh (request: HttpServletRequest): ResponseEntity<Void> {
        return authService.refreshAccessToken(request)
    }

    /**
     * 로그아웃 API
     */
    @PostMapping("/logout")
    fun logout(): ResponseEntity<Void> {
        return authService.logout()
    }
}