package schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.datetime.datetime

object TastingNotes : Table("TastingNotes") {
    val id = integer("id").autoIncrement()
    val alcoholId: Column<Int> = reference("alcohol_id", Alcohols.id)
    val userId: Column<Long> = reference("user_id", Users.id)
    val commentId: Column<Int> = reference("comment_id", Comments.id)
    val title = varchar("title", 255).nullable()
    val image = blob("image").nullable()
    val aromaRate = decimal("aroma_rate", 5, 2).nullable()
    val palateRate = decimal("palate_rate", 5, 2).nullable()
    val finishRate = decimal("finish_rate", 5, 2).nullable()
    val aromaNote = text("aroma_note").nullable()
    val palateNote = text("palate_note").nullable()
    val finishNote = text("finish_note").nullable()
    val createdAt = datetime("created_at").nullable()
    val updatedAt = datetime("updated_at").nullable()

    override val primaryKey = PrimaryKey(id)
}
