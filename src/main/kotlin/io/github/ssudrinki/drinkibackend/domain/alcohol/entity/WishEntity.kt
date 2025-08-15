package io.github.ssudrinki.drinkibackend.domain.alcohol.entity

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import schema.Wishes

/**
 * 사용자의 술 위시리스트를 나타내는 Entity 클래스
 *
 * 이 클래스는 Alcohols 테이블과 매핑되며, Exposed DAO 패턴을 사용합니다.
 */
class WishEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<WishEntity>(Wishes)

    var userId by Wishes.userId
    var alcoholId by Wishes.alcoholId
    var createdAt by Wishes.createdAt
}