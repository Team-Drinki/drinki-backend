package io.github.teamdrinki.drinkibackend.domain.auth.service

import io.github.teamdrinki.drinkibackend.domain.auth.util.AuthCookieFactory
import io.github.teamdrinki.drinkibackend.domain.auth.util.JwtUtil
import io.github.teamdrinki.drinkibackend.common.util.addCookies
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwt: JwtUtil
) {
    /**
     * Access Token을 재발급합니다.
     * 리프레시 토큰이 유효한 경우 새로운 액세스 토큰을 발급합니다.
     * 새로 발급된 Access Token과 기존 Refresh Token을 쿠키에 담아 응답합니다.
     *
     * @param request HTTP 요청 객체
     * @return HTTP 응답 객체 (204 No Content 또는 401 Unauthorized)
     */
    fun refreshAccessToken(
        request: HttpServletRequest,
    ): ResponseEntity<Void> {
        val refreshCookie = request.cookies?.firstOrNull { it.name == "refreshToken" }
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        val refreshToken = refreshCookie.value

        val userId = runCatching { jwt.getUserIdFromToken(refreshToken) }
            .getOrElse { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() }

        val newAccessToken = jwt.createAccessToken(userId)

        val accessCookie = AuthCookieFactory.access(newAccessToken)
        val refreshCookieOut = AuthCookieFactory.refresh(refreshToken)

        return ResponseEntity.noContent()
            .addCookies(accessCookie, refreshCookieOut)
            .build()
    }

    /**
     * 로그아웃 요청을 처리합니다.
     * Access Token과 Refresh Token을 쿠키에서 삭제합니다.
     *
     * @return HTTP 응답 객체 (204 No Content)
     */
    fun logout(): ResponseEntity<Void> {
        val expiredAccessCookie = AuthCookieFactory.delete("accessToken")
        val expiredRefreshCookie = AuthCookieFactory.delete("refreshToken")

        return ResponseEntity.noContent()
            .addCookies(expiredAccessCookie, expiredRefreshCookie)
            .build()
    }
}