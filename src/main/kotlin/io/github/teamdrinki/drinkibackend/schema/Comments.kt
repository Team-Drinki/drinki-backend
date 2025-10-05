package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.datetime.datetime

enum class CommentTargetType { post, tasting_note, alcohol }

object Comments : IntIdTable("comments") {
    val userId = reference("user_id", Users.id)
    val targetType = enumerationByName("target_type", 20, CommentTargetType::class)
    val targetId = integer("target_id")
    val parentId = reference("parent_id", id).nullable()
    val body = text("body")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
