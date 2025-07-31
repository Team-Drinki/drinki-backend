package io.github.teamdrinki.drinkibackend

import io.github.ssudrinki.drinkibackend.auth.JwtProvider
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.Duration
import kotlin.test.assertEquals

@SpringBootTest
class DrinkibackendApplicationTests {

	@Test
	fun contextLoads() {
	}
	@Test
	fun createAccessToken() {
		val jp = JwtProvider("mysupersecret...", Duration.ofMinutes(15))
		val token = jp.createAccessToken(123L)
		val decoded = jp.verifyToken(token)
		assertEquals("123", decoded.subject)
	}

}
