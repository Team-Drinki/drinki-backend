package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object AlcoholStyles : IntIdTable("AlcoholStyles") {
    val categoryId = reference("category_id", AlcoholCategories.id)
    val name = varchar("name", 255) // not null
}
