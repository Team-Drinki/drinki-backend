package schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime

enum class PostCategory { free, question }

object Posts : Table("Posts") {
    val id = integer("id").autoIncrement()
    val userId = reference("user_id", Users.id)
    val commentId = reference("comment_id", Comments.id)
    val title = varchar("title", 255)
    val image = blob("image").nullable()
    val category = enumerationByName("category", 20, PostCategory::class)
    val body = text("body")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
