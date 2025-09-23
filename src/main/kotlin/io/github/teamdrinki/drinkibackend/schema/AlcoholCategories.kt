package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object AlcoholCategories : IntIdTable("AlcoholCategories") {
    val name = varchar("name", 255)
}
