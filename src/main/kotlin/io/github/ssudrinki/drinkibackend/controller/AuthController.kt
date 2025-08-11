package io.github.ssudrinki.drinkibackend.controller

import io.github.ssudrinki.drinkibackend.auth.JwtProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration

@RestController
@RequestMapping("/auth")
class AuthController (private val jwt: JwtProvider) {

    /**
     * 구글 로그인 API
     */
    @PostMapping("/login/google")
    fun loginGoogle(): String {
        return "redirect:/oauth2/authorization/google"
    }

    /**
     * 리프레시 토큰 발급 API
     */
    @PostMapping("/refresh")
    fun createRefreshToken (
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): ResponseEntity<Map<String, String>> {
        val refreshCookie = request.cookies?.firstOrNull { it.name == "refreshToken" }
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        val refresh = refreshCookie.value

        val userId = runCatching { jwt.verifyToken(refresh).subject.toLong() }
            .getOrElse { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() }

        val newAccessToken = jwt.createAccessToken(userId)
        val newRefreshToken = jwt.createRefreshToken(userId)

        // TODO : 리프레시 토큰은 재발급하지 않고, 기존 토큰을 그대로 사용
        val cookie = ResponseCookie.from("refreshToken", newRefreshToken)
            .httpOnly(true).secure(true).sameSite("None")
            .path("/").maxAge(Duration.ofDays(14))
            .build()
        response.addHeader("Set-Cookie", cookie.toString())

        return ResponseEntity.ok(mapOf("accessToken" to newAccessToken))
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

        response.addHeader("Set-Cookie", expireRefreshCookie)
        response.addHeader("Set-Cookie", expireAccessCookie)
        response.status = HttpStatus.NO_CONTENT.value()
    }
}
