package io.github.ssudrinki.drinkibackend.db

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.dao.LongEntity
import org.jetbrains.exposed.v1.dao.LongEntityClass


object OAuthUsers : LongIdTable("oauth_user") {
    val email      = varchar("email", 255).uniqueIndex().nullable()
    val socialType = varchar("social_type", 20)
    val socialId   = varchar("social_id", 255).uniqueIndex().nullable()
}

class OAuthUserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<OAuthUserEntity>(OAuthUsers)

    var email      by OAuthUsers.email
    var socialType by OAuthUsers.socialType
    var socialId   by OAuthUsers.socialId
}