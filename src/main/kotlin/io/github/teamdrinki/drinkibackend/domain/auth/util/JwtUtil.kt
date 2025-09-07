package io.github.teamdrinki.drinkibackend.domain.auth.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.util.Date

/**
 * JWT 토큰 생성 및 검증을 위한 유틸리티 클래스입니다.
 *
 * @property secret JWT 서명에 사용되는 비밀 키
 * @property accessExp Access Token 만료 기간
 * @property refreshExp Refresh Token 만료 기간
 */
@Component
class JwtUtil (
    @Value("\${spring.jwt.secret}") private val secret: String? = null,
    @Value("\${spring.jwt.access-exp}") private val accessExp: Duration? = null,
    @Value("\${spring.jwt.refresh-exp}") private val refreshExp: Duration? = null) {
    private val algorithm = Algorithm.HMAC256(secret)

    /**
     * 사용자 ID를 기반으로 Access Token을 생성합니다.
     *
     * @param userId 사용자 ID
     * @return 생성된 Access Token 문자열
     */
    fun createAccessToken(userId: Long): String =
        JWT.create()
            .withSubject(userId.toString())
            .withExpiresAt(Date.from(Instant.now().plus(accessExp ?: Duration.ZERO)))
            .sign(algorithm)

    /**
     * 사용자 ID를 기반으로 Refresh Token을 생성합니다.
     *
     * @param userId 사용자 ID
     * @return 생성된 Refresh Token 문자열
     */
    fun createRefreshToken(userId: Long): String =
        JWT.create()
            .withSubject(userId.toString())
            .withExpiresAt(Date.from(Instant.now().plus(refreshExp ?: Duration.ZERO)))
            .sign(algorithm)

    /**
     * 주어진 토큰을 검증하고 디코딩된 JWT 객체를 반환합니다.
     *
     * @param token 검증할 JWT 토큰 문자열
     * @return 디코딩된 JWT 객체
     */
    fun verifyToken(token: String): DecodedJWT =
        JWT.require(algorithm).build().verify(token)

    /**
     * 토큰에서 사용자 ID를 추출합니다.
     *
     * @param token 사용자 ID를 추출할 JWT 토큰 문자열
     * @return 토큰에서 추출한 사용자 ID
     */
    fun getUserIdFromToken(token: String): Long =
        verifyToken(token).subject.toLong()
}