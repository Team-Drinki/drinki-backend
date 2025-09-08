package io.github.teamdrinki.drinkibackend.common.util

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity

/**
 * HttpServletResponse의 확장 함수로 응답 헤더에 여러 개의 쿠키를 추가합니다.
 *
 * @param cookies 추가할 쿠키 목록
 */
fun HttpServletResponse.addCookies(vararg cookies: ResponseCookie) {
    cookies.forEach { cookie ->
        this.addHeader(HttpHeaders.SET_COOKIE, cookie.toString())
    }
}

/**
 * ResponseEntity.HeadersBuilder의 확장 함수로 응답 헤더에 여러 개의 쿠키를 추가합니다.
 *
 * @param cookies 추가할 쿠키 목록
 * @return ResponseEntity.HeadersBuilder<T> 현재 빌더 인스턴스
 */
fun <T : ResponseEntity.HeadersBuilder<T>>
    ResponseEntity.HeadersBuilder<T>.addCookies(vararg cookies: ResponseCookie): T {
    cookies.forEach { cookie ->
        this.header(HttpHeaders.SET_COOKIE, cookie.toString())
    }
    @Suppress("UNCHECKED_CAST")
    return this as T
}