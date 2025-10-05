package io.github.teamdrinki.drinkibackend.domain.auth.config

import io.github.teamdrinki.drinkibackend.domain.auth.util.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

/**
 * JWT 인증을 처리하는 필터 클래스입니다.
 */
class JwtAuthFilter (
    private val jwt: JwtUtil
): OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(javaClass)

    /**
     * 각 요청마다 JWT 토큰을 검사하고 인증을 처리합니다.
     *
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @param filterChain 필터 체인 객체
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        log.debug("JwtAuthFilter invoked path={}", request.requestURI)
        val header = request.getHeader(HttpHeaders.AUTHORIZATION)
        val bearerToken = header
            ?.takeIf { it.startsWith("Bearer ", ignoreCase = true) }
            ?.removePrefix("Bearer ")
            ?.trim()

        val cookieToken = request.cookies
            ?.firstOrNull { it.name == "accessToken" }
            ?.value

        val token = bearerToken ?: cookieToken
        if (token.isNullOrBlank()) {
            filterChain.doFilter(request, response)
            return
        }

        val decoded = runCatching { jwt.verifyToken(token) }
            .getOrElse {
                println("Failed to verify token")
                filterChain.doFilter(request, response)
                return
            }

        val userId = jwt.getUserIdFromToken(token)
        val auth = UsernamePasswordAuthenticationToken(userId, null, emptyList())
        SecurityContextHolder.getContext().authentication = auth

        println("Token authenticated")
        filterChain.doFilter(request, response)
    }
}