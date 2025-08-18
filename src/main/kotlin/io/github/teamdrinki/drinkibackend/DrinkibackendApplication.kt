package io.github.teamdrinki.drinkibackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class DrinkibackendApplication

fun main(args: Array<String>) {
	runApplication<DrinkibackendApplication>(*args)
}
