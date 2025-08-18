package io.github.ssudrinki.drinkibackend.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import schema.UserEntity
import schema.Users
import java.time.Duration

@Component
class GoogleAuthSuccessHandler (
    private val jwt: JwtProvider,
    @Value("\${client.url}") private val clientUrl: String
): AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val principal = authentication.principal as OAuth2User
        val socialId = principal.attributes["sub"] as String

        val user = transaction {
            UserEntity.find { Users.socialId eq socialId }.single()
        }

        val accessToken = jwt.createAccessToken(user.id.value)
        val refreshToken = jwt.createRefreshToken(user.id.value)

        val accessCookie = ResponseCookie
            .from("accessToken", accessToken)
            .httpOnly(true).secure(true).sameSite("None")
            .path("/").maxAge(Duration.ofMinutes(10))  // 1시간
            .build()

        val refreshCookie = ResponseCookie
            .from("refreshToken", refreshToken)
            .httpOnly(true).secure(true).sameSite("None")
            .path("/").maxAge(Duration.ofDays(14))
            .build()

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString())
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString())

        response.sendRedirect("$clientUrl/")
    }
}