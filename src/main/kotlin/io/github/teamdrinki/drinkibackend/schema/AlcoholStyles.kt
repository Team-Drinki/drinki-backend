package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass


object AlcoholStyles : IntIdTable("AlcoholStyles") {
    val categoryId = reference("category_id", AlcoholCategories.id)
    val name = varchar("name", 255) // not null
}

class AlcoholStyle(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AlcoholStyle>(AlcoholStyles)

    var categoryId by AlcoholCategory referencedOn AlcoholStyles.categoryId
    var name by AlcoholStyles.name
}
