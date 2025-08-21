package io.github.ssudrinki.drinkibackend.domain.auth.service

import io.github.ssudrinki.drinkibackend.domain.auth.util.AuthCookieFactory
import io.github.ssudrinki.drinkibackend.domain.auth.util.JwtUtil
import io.github.ssudrinki.drinkibackend.common.util.addCookies
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwt: JwtUtil
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

        val accessCookie = AuthCookieFactory.access(newAccessToken)
        val refreshCookieOut = AuthCookieFactory.refresh(refreshToken)

        return ResponseEntity.noContent()
            .addCookies(accessCookie, refreshCookieOut)
            .build()
    }

    fun logout(): ResponseEntity<Void> {
        val expiredAccessCookie = AuthCookieFactory.delete("accessToken")
        val expiredRefreshCookie = AuthCookieFactory.delete("refreshToken")

        return ResponseEntity.noContent()
            .addCookies(expiredAccessCookie, expiredRefreshCookie)
            .build()
    }
}