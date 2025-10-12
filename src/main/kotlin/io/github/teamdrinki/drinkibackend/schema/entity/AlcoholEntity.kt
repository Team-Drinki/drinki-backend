package io.github.teamdrinki.drinkibackend.schema.entity

import io.github.teamdrinki.drinkibackend.schema.AlcoholCategories
import io.github.teamdrinki.drinkibackend.schema.AlcoholLocations
import io.github.teamdrinki.drinkibackend.schema.AlcoholStyles
import io.github.teamdrinki.drinkibackend.schema.Alcohols
import io.github.teamdrinki.drinkibackend.schema.TastingNotes
import io.github.teamdrinki.drinkibackend.schema.UserEntity
import io.github.teamdrinki.drinkibackend.schema.Users
import io.github.teamdrinki.drinkibackend.schema.Wishes
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class AlcoholEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AlcoholEntity>(Alcohols)

    var user        by UserEntity referencedOn Alcohols.userId
    var name        by Alcohols.name
    var imageUrl    by Alcohols.imageUrl
    var price       by Alcohols.price
    var proof       by Alcohols.proof
    var rating      by Alcohols.rating
    var wishCnt     by Alcohols.wishCnt
    var viewCnt     by Alcohols.viewCnt
    var noteCnt     by Alcohols.noteCnt
    var content     by Alcohols.content
    val wishes      by WishEntity referrersOn Wishes.alcoholId
    var category    by AlcoholCategoryEntity referencedOn Alcohols.category
    var style       by AlcoholStyleEntity referencedOn Alcohols.style
    var location    by AlcoholLocationEntity referencedOn Alcohols.location
    var createdAt   by Alcohols.createdAt
    var updatedAt   by Alcohols.updatedAt

    // wish 카운트를 동적으로 계산
    val wishCount: Long
        get() = wishes.count()

    // 특정 사용자의 wish 여부 확인
    fun isWishedByUser(userId: Long?): Boolean {
        return userId?.let {
            wishes.any { wish -> wish.user.id.value.toLong() == userId }
        } ?: false
    }
}