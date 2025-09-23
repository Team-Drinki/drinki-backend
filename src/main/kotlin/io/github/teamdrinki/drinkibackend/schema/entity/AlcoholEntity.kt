package io.github.teamdrinki.drinkibackend.schema.entity

import io.github.teamdrinki.drinkibackend.schema.AlcoholCategories
import io.github.teamdrinki.drinkibackend.schema.AlcoholLocations
import io.github.teamdrinki.drinkibackend.schema.AlcoholStyles
import io.github.teamdrinki.drinkibackend.schema.Alcohols
import io.github.teamdrinki.drinkibackend.schema.TastingNotes
import io.github.teamdrinki.drinkibackend.schema.UserEntity
import io.github.teamdrinki.drinkibackend.schema.Users
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class AlcoholEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AlcoholEntity>(Alcohols)

    var tastingNote by TastingNoteEntity referencedOn Alcohols.tastingNoteId
    var user        by UserEntity referencedOn Alcohols.userId
    var name        by Alcohols.name
    var imageUrl    by Alcohols.image_url
    var price       by Alcohols.price
    var proof       by Alcohols.proof
    var rating      by Alcohols.rating
    var wish        by Alcohols.wish
    var viewCnt     by Alcohols.viewCnt
    var noteCnt     by Alcohols.noteCnt
    var content     by Alcohols.content
    var category    by AlcoholCategoryEntity referencedOn Alcohols.categoryId
    var style       by AlcoholStyleEntity referencedOn Alcohols.styleId
    var location    by AlcoholLocationEntity referencedOn Alcohols.locationId
    var createdAt   by Alcohols.createdAt
    var updatedAt   by Alcohols.updatedAt
}