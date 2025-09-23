package io.github.teamdrinki.drinkibackend.schema.entity

import io.github.teamdrinki.drinkibackend.schema.AlcoholCategories
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class AlcoholCategoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AlcoholCategoryEntity>(AlcoholCategories)

    var name by AlcoholCategories.name
}