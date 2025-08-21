package io.github.teamdrinki.drinkibackend.domain.auth.config

import io.github.teamdrinki.drinkibackend.domain.auth.util.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter (
    private val jwt: JwtUtil
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val bearer = request.getHeader("Authorization")
        if (bearer == null || !bearer.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = bearer.removePrefix("Bearer ").trim()

        val decoded = runCatching { jwt.verifyToken(token) }
            .getOrElse {
                println("Failed to verify token")
                filterChain.doFilter(request, response)
                return
            }

        val userId = decoded.subject.toLong()
        val auth = UsernamePasswordAuthenticationToken(userId, null, emptyList())
        SecurityContextHolder.getContext().authentication = auth

        println("Token authenticated")
        filterChain.doFilter(request, response)
    }
}