package schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.datetime.datetime

object AdminPosts : Table("admin_posts") {
    val id = long("id").autoIncrement()
    val title = varchar("title", 100)
    val content = text("content")
    val postType = varchar("post_type", 20) // enum('NOTICE', 'FAQ')
    val writer: Column<Long> = reference("writer", Users.id)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
