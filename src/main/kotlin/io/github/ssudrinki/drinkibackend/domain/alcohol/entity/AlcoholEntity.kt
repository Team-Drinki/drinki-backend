package io.github.ssudrinki.drinkibackend.domain.alcohol.entity

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.datetime.datetime
import org.springframework.context.annotation.Bean
import schema.*

/**
 * 술 정보를 나타내는 Entity 클래스
 *
 * 이 클래스는 Alcohols 테이블과 매핑되며, Exposed DAO 패턴을 사용합니다.
 */
class AlcoholEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AlcoholEntity>(Alcohols)

    var tastingNoteId by Alcohols.tastingNoteId
    var userId by Alcohols.userId
    var name by Alcohols.name
    var imageUrl by Alcohols.image_url
    var price by Alcohols.price
    var proof by Alcohols.proof
    var categoryId by Alcohols.categoryId
    var styleId by Alcohols.styleId
    var locationId by Alcohols.locationId
    var content by Alcohols.content
    var rating by Alcohols.rating
    var createdAt by Alcohols.createdAt
    var updatedAt by Alcohols.updatedAt
}