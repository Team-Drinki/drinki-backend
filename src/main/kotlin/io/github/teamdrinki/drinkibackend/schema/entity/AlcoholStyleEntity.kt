package io.github.teamdrinki.drinkibackend.schema.entity

import io.github.teamdrinki.drinkibackend.schema.AlcoholStyles
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class AlcoholStyleEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AlcoholStyleEntity>(AlcoholStyles)

    var categoryId by AlcoholCategoryEntity referencedOn AlcoholStyles.categoryId
    var name by AlcoholStyles.name
}