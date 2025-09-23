package io.github.teamdrinki.drinkibackend.schema.entity

import io.github.teamdrinki.drinkibackend.schema.AlcoholLocations
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class AlcoholLocationEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AlcoholLocationEntity>(AlcoholLocations)

    var name by AlcoholLocations.name
}