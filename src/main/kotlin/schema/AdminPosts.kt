package schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime

enum class AdminPostType { NOTICE, FAQ }

object AdminPosts : Table("admin_posts") {
    val id = long("id").autoIncrement()
    val title = varchar("title", 100)
    val content = text("content")
    val postType = varchar("post_type", 20) // or: enumerationByName("post_type", 20, AdminPostType::class)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
    val writer = reference("writer", Users.id)

    override val primaryKey = PrimaryKey(id)
}
