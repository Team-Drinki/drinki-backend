package schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.datetime.datetime

object Reactions : Table("Reactions") {
    val id = integer("id").autoIncrement()
    val userId: Column<Long> = reference("user_id", Users.id)
    val targetType = enumerationByName("target_type", 20, ReactionTargetType::class)
    val targetId = integer("target_id")
    val reactionType = enumerationByName("reaction_type", 10, ReactionType::class)
    val createdAt = datetime("created_at").nullable()
    val updatedAt = datetime("updated_at").nullable()

    override val primaryKey = PrimaryKey(id)
}
