package io.github.teamdrinki.drinkibackend.schema.entity

import io.github.teamdrinki.drinkibackend.schema.TastingNotes
import io.github.teamdrinki.drinkibackend.schema.UserEntity
import io.github.teamdrinki.drinkibackend.schema.Wishes
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class WishEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<WishEntity>(Wishes)

    var user      by UserEntity referencedOn Wishes.userId
    var alcohol   by AlcoholEntity referencedOn Wishes.alcoholId
    var createdAt by Wishes.createdAt
}