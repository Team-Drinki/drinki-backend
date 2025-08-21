package io.github.teamdrinki.drinkibackend.domain.auth.config

import io.github.teamdrinki.drinkibackend.common.util.addCookies
import io.github.teamdrinki.drinkibackend.domain.auth.util.AuthCookieFactory
import io.github.teamdrinki.drinkibackend.domain.auth.util.JwtUtil
import io.github.teamdrinki.drinkibackend.schema.UserEntity
import io.github.teamdrinki.drinkibackend.schema.Users
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component


@Component
class GoogleAuthSuccessHandler (
    private val jwt: JwtUtil,
    @Value("\${client.url}") private val clientUrl: String
): AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val principal = authentication.principal as OAuth2User
        val socialId = principal.attributes["sub"] as String

        val userId = getUserIdBySocialId(socialId)

        val accessToken = jwt.createAccessToken(userId)
        val refreshToken = jwt.createRefreshToken(userId)

        val accessCookie = AuthCookieFactory.access(accessToken)
        val refreshCookie = AuthCookieFactory.refresh(refreshToken)

        response.addCookies(accessCookie, refreshCookie)
        response.sendRedirect("$clientUrl/")
    }

    private fun getUserIdBySocialId(socialId: String): Long {
        return transaction {
            UserEntity
                .find { Users.socialId eq socialId }
                .single()
                .id.value
        }
    }
}