package io.github.teamdrinki.drinkibackend.presentation.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/")
    fun home(): String {
        return "Hello, Welcome to Drinki Backend!"
    }
}