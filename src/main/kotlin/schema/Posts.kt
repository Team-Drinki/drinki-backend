package schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.datetime.datetime

object Posts : Table("Posts") {
    val id = integer("id").autoIncrement()
    val userId: Column<Long> = reference("user_id", Users.id)
    val commentId: Column<Int> = reference("comment_id", Comments.id)
    val title = varchar("title", 255).nullable()
    val image = blob("image").nullable()
    val category = enumerationByName("category", 20, PostCategory::class).nullable()
    val body = text("body").nullable()
    val createdAt = datetime("created_at").nullable()
    val updatedAt = datetime("updated_at").nullable()

    override val primaryKey = PrimaryKey(id)
}
