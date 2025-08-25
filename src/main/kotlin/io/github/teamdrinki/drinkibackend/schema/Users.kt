package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.datetime.datetime
import org.jetbrains.exposed.v1.datetime.CurrentDateTime

enum class UserRole { USER, ADMIN }

object Users : LongIdTable("Users") {
    val socialType = varchar("social_type", 50)
    val socialId = varchar("social_id", 100).nullable()
    val nickname = varchar("nickname", 20)
    val profileImageUrl = varchar("profile_image_url", 255).nullable()
    val role = varchar("role", 255) // or use: enumerationByName("role", 20, UserRole::class)
    val isDeleted = bool("is_deleted").default(false)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}