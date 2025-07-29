package schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.datetime.datetime

object Comments : Table("Comments") {
    val id = integer("id").autoIncrement()
    val userId: Column<Long> = reference("user_id", Users.id)
    val targetType = enumerationByName("target_type", 20, TargetType::class)
    val targetId = integer("target_id")
    val parentId: Column<Int?> = reference("parent_id", id).nullable()
    val body = text("body")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
