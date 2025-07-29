package schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime

object Users : Table("Users") {
    val id = long("id").autoIncrement()
    val socialType = varchar("social_type", 50)
    val socialId = varchar("social_id", 100)
    val nickname = varchar("nickname", 20)
    val profileImageUrl = varchar("profile_image_url", 255).nullable()
    val role = varchar("role", 255)
    val isDeleted = char("is_deleted", 1)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
