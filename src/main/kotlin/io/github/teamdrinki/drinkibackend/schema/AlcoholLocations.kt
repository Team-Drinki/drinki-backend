package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

object AlcoholLocations : IntIdTable("AlcoholLocations") {
    val name = varchar("name", 255)
}

class AlcoholLocation(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AlcoholLocation>(AlcoholLocations)

    var name by AlcoholLocations.name
}
