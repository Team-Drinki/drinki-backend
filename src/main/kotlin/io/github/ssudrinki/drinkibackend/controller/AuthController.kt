package io.github.ssudrinki.drinkibackend.controller

import io.github.ssudrinki.drinkibackend.auth.JwtProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration

@RestController
@RequestMapping("/api/v1/auth")
class AuthController (private val jwt: JwtProvider) {

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
    fun refreshAccessToken (
        request: HttpServletRequest,
        response: HttpServletResponse,
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

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString())

        return ResponseEntity.status(HttpStatus.OK).build()
    }

    /**
     * 로그아웃 API
     */
    @PostMapping("/logout")
    fun logout(response: HttpServletResponse) {
        val expireRefreshCookie = ResponseCookie
            .from("refreshToken", "")
            .httpOnly(true).secure(true).sameSite("None")
            .path("/").maxAge(0)
            .build()
            .toString()

        val expireAccessCookie = ResponseCookie
            .from("accessToken", "")
            .httpOnly(true).secure(true).sameSite("None")
            .path("/").maxAge(0)
            .build()
            .toString()

        response.addHeader(HttpHeaders.SET_COOKIE, expireRefreshCookie)
        response.addHeader(HttpHeaders.SET_COOKIE, expireAccessCookie)
        response.status = HttpStatus.NO_CONTENT.value()
    }
}
