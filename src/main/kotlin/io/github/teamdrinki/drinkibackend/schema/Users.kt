package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime

enum class UserRole { USER, ADMIN }

object Users : Table("Users") {
    val id = long("id").autoIncrement()
    val socialType = varchar("social_type", 50)
    val socialId = varchar("social_id", 100).nullable()
    val nickname = varchar("nickname", 20)
    val profileImageUrl = varchar("profile_image_url", 255).nullable()
    val role = varchar("role", 255) // or use: enumerationByName("role", 20, UserRole::class)
    val isDeleted = char("is_deleted", 1)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
