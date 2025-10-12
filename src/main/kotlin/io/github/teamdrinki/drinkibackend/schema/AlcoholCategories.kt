package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

object AlcoholCategories : IntIdTable("AlcoholCategories") {
    val name = varchar("name", 255)
}

class AlcoholCategory(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AlcoholCategory>(AlcoholCategories)

    var name by AlcoholCategories.name
}
