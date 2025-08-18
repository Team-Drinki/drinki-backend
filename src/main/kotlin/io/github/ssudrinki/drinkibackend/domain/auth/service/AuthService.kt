package io.github.ssudrinki.drinkibackend.domain.auth.service

import io.github.ssudrinki.drinkibackend.domain.auth.config.JwtProvider
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class AuthService(
    private val jwt: JwtProvider
) {
    fun refreshAccessToken(
        request: HttpServletRequest,
    ): ResponseEntity<Void> {
        val refreshCookie = request.cookies?.firstOrNull { it.name == "refreshToken" }
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        val refreshToken = refreshCookie.value

        val userId = runCatching { jwt.verifyToken(refreshToken).subject.toLong() }
            .getOrElse { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() }

        val newAccessToken = jwt.createAccessToken(userId)

        val accessCookie = ResponseCookie
            .from("accessToken", newAccessToken)
            .httpOnly(true).secure(true).sameSite("None")
            .path("/").maxAge(Duration.ofMinutes(10))  // 1시간
            .build()
            .toString()

        val refreshCookieOut = ResponseCookie
            .from("refreshToken", refreshToken)
            .httpOnly(true).secure(true).sameSite("None")
            .path("/").maxAge(Duration.ofDays(14))
            .build()
            .toString()

        return ResponseEntity.noContent()
            .header(HttpHeaders.SET_COOKIE, accessCookie)
            .header(HttpHeaders.SET_COOKIE, refreshCookieOut)
            .build()
    }

    fun logout(): ResponseEntity<Void> {
        val expireAccessCookie = ResponseCookie
            .from("accessToken", "")
            .httpOnly(true).secure(true).sameSite("None")
            .path("/").maxAge(0)
            .build()
            .toString()

        val expireRefreshCookie = ResponseCookie
            .from("refreshToken", "")
            .httpOnly(true).secure(true).sameSite("None")
            .path("/").maxAge(0)
            .build()
            .toString()

        return ResponseEntity.noContent()
            .header(HttpHeaders.SET_COOKIE, expireAccessCookie)
            .header(HttpHeaders.SET_COOKIE, expireRefreshCookie)
            .build()
    }
}