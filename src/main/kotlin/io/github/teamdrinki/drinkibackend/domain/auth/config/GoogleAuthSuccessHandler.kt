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


/**
 * OAuth2 인증 성공 시 처리하는 핸들러 클래스입니다.
 */
@Component
class GoogleAuthSuccessHandler (
    private val jwt: JwtUtil,
    @Value("\${client.url}") private val clientUrl: String
): AuthenticationSuccessHandler {
    /**
     * OAuth2 인증 성공 시 호출됩니다.
     * 사용자 ID로 Access Token과 Refresh Token을 생성하고,
     * 이를 쿠키에 담아 클라이언트로 전송합니다.
     * 이후 클라이언트를 지정된 URL로 리다이렉트합니다.
     *
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @param authentication 인증 객체
     */
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

    /**
     * 소셜 ID로 사용자 ID를 조회합니다.
     *
     * @param socialId 소셜 ID
     * @return 사용자 ID
     */
    private fun getUserIdBySocialId(socialId: String): Long {
        return transaction {
            UserEntity
                .find { Users.socialId eq socialId }
                .single()
                .id.value
        }
    }
}