package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime

enum class ReactionTargetType { post, comment, alcohol, tasting_note }
enum class ReactionType { like, unlike }

object Reactions : Table("Reactions") {
    val id = integer("id").autoIncrement()
    val userId = reference("user_id", Users.id)
    val targetType = enumerationByName("target_type", 20, ReactionTargetType::class)
    val targetId = integer("target_id")
    val reactionType = enumerationByName("reaction_type", 10, ReactionType::class)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
