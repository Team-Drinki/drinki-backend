package io.github.teamdrinki.drinkibackend.presentation.controller

import io.github.teamdrinki.drinkibackend.domain.auth.service.AuthService
import io.github.teamdrinki.drinkibackend.domain.auth.util.JwtUtil
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 인증 관련 API 요청을 처리하는 컨트롤러 클래스입니다.
 */
@RestController
@RequestMapping("/api/v1/auth")
class AuthController (private val authService: AuthService, private val jwtUtil: JwtUtil) {
    /**
     * 구글 OAuth2 로그인 요청을 처리하는 API 엔드포인트입니다.
     *
     * @return 구글 OAuth2 인증 페이지로 리다이렉트하는 URL
     */
    @PostMapping("/login/google")
    fun loginGoogle(): String {
        return "redirect:/oauth2/authorization/google"
    }

    /**
     * Access Token을 재발급하는 API 엔드포인트입니다.
     * 리프레시 토큰이 유효한 경우 새로운 액세스 토큰을 발급합니다.
     * 새로 발급된 Access Token과 기존 Refresh Token을 쿠키에 담아 응답합니다.
     *
     * @param request HTTP 요청 객체
     * @return HTTP 응답 객체 (204 No Content 또는 401 Unauthorized)
     */
    @PostMapping("/refresh")
    fun refresh (request: HttpServletRequest): ResponseEntity<Void> {
        return authService.refreshAccessToken(request)
    }

    /**
     * 로그아웃 요청을 처리하는 API 엔드포인트입니다.
     * Access Token과 Refresh Token을 쿠키에서 삭제합니다.
     *
     * @return HTTP 응답 객체 (204 No Content)
     */
    @PostMapping("/logout")
    fun logout(): ResponseEntity<Void> {
        return authService.logout()
    }
}