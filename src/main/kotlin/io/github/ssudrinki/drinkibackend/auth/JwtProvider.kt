package io.github.ssudrinki.drinkibackend.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.util.Date

@Component
class JwtProvider (
    @Value("\${spring.jwt.secret}") private val secret: String? = null,
    @Value("\${spring.jwt.access-exp}") private val accessExp: Duration? = null,
    @Value("\${spring.jwt.refresh-exp}") private val refreshExp: Duration? = null) {
    private val algorithm = Algorithm.HMAC256(secret)

    fun createAccessToken(userId: Long) =
        JWT.create()
            .withSubject(userId.toString())
            .withExpiresAt(Date.from(Instant.now().plus(accessExp ?: Duration.ZERO)))
            .sign(algorithm)

    fun createRefreshToken(userId: Long) =
        JWT.create()
            .withSubject(userId.toString())
            .withExpiresAt(Date.from(Instant.now().plus(refreshExp ?: Duration.ZERO)))
            .sign(algorithm)

    fun verifyToken(token: String): DecodedJWT =
        JWT.require(algorithm).build().verify(token)
}