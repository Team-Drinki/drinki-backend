package io.github.ssudrinki.drinkibackend.domain.auth.util

import org.springframework.http.ResponseCookie
import java.time.Duration

/**
 * 인증용 쿠키를 생성하는 팩토리 클래스입니다.
 */
object AuthCookieFactory {
    /**
     * 인증용 액세스 토큰 쿠키를 생성합니다.
     *
     * @param token 액세스 토큰
     * @param secure 쿠키의 보안 설정 (기본값: true)
     * @param sameSite SameSite 속성 설정 (기본값: "None")
     * @return 생성된 ResponseCookie 객체
     */
    fun access(token: String, secure: Boolean = true, sameSite: String = "None"): ResponseCookie =
        ResponseCookie
            .from("accessToken", token)
            .httpOnly(true).secure(secure).sameSite(sameSite)
            .path("/").maxAge(Duration.ofMinutes(10))
            .build()

    /**
     * 인증용 리프레시 토큰 쿠키를 생성합니다.
     *
     * @param token 리프레시 토큰
     * @param secure 쿠키의 보안 설정 (기본값: true)
     * @param sameSite SameSite 속성 설정 (기본값: "None")
     * @return 생성된 ResponseCookie 객체
     */
    fun refresh(token: String, secure: Boolean = true, sameSite: String = "None"): ResponseCookie =
        ResponseCookie
            .from("refreshToken", token)
            .httpOnly(true).secure(secure).sameSite(sameSite)
            .path("/").maxAge(Duration.ofDays(14))
            .build()

    /**
     * 인증용 쿠키를 삭제합니다.
     *
     * @param name 삭제할 쿠키의 이름
     * @param secure 쿠키의 보안 설정 (기본값: true)
     * @param sameSite SameSite 속성 설정 (기본값: "None")
     * @return 삭제된 쿠키를 담은 ResponseCookie 객체
     */
    fun delete(name: String, secure: Boolean = true, sameSite: String = "None"): ResponseCookie =
        ResponseCookie
            .from(name, "")
            .httpOnly(true).secure(secure).sameSite(sameSite)
            .path("/")
            .maxAge(Duration.ZERO)
            .build()
}