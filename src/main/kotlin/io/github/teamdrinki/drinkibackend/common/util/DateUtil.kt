package io.github.teamdrinki.drinkibackend.common.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 날짜 관련 유틸리티 클래스
 */
object DateUtil {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    /**
     * 날짜를 "yyyy-MM-dd HH:mm:ss" 형식의 문자열로 포맷합니다.
     *
     * @param dateTime 포맷할 날짜
     * @return 포맷된 날짜 문자열
     */
    fun changeToFormattedTime(dateTime: LocalDateTime): String {
        return dateTime.format(formatter)
    }
}