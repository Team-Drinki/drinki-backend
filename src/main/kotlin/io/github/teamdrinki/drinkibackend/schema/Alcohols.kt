package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.datetime.datetime

/**
 * 술 정보를 저장하는 Table
 *
 * Exposed DAO 패턴을 사용합니다.
 */
object Alcohols : IntIdTable("Alcohols") {
    val userId     = reference("user_id", Users)
    val name       = varchar("name", 255)
    val imageUrl   = text("image_url")
    val price      = decimal("price", 20, 5)
    val proof      = float("proof")
    val rating     = decimal("rating", 3, 2)                // 5점 만점 (0.0 ~ 5.0)
    val wishCnt    = integer("wish_cnt")
    val viewCnt    = integer("view_cnt")
    val noteCnt    = integer("note_cnt")
    val content    = text("content")
    val category = reference("category_id", AlcoholCategories)
    val style    = reference("style_id", AlcoholStyles)
    val location = reference("location_id", AlcoholLocations)

    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}

class Alcohol(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Alcohol>(Alcohols)

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
    val wishes      by Wish referrersOn Wishes.alcoholId
    var category    by AlcoholCategory referencedOn Alcohols.category
    var style       by AlcoholStyle referencedOn Alcohols.style
    var location    by AlcoholLocation referencedOn Alcohols.location
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
