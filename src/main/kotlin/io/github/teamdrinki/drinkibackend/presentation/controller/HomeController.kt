package io.github.teamdrinki.drinkibackend.presentation.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    /**
     * 루트 페이지 요청을 처리하는 API 엔드포인트입니다.
     *
     * @return Hello, Welcome to Drinki Backend!
     */
    @GetMapping("/")
    fun home(): String {
        return "Hello, Welcome to Drinki Backend!"
    }
}