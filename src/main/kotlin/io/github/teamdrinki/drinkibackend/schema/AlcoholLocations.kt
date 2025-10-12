package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object AlcoholLocations : IntIdTable("AlcoholLocations") {
    val name = varchar("name", 255)
}
