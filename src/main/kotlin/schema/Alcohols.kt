package schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.datetime.datetime

object Alcohols : Table("Alcohols") {
    val id = integer("id").autoIncrement()
    val tastingNoteId: Column<Int> = reference("tasting_note_id", TastingNotes.id)
    val userId: Column<Long> = reference("user_id", Users.id)
    val commentId: Column<Int> = reference("comment_id", Comments.id)
    val title = varchar("title", 255).nullable()
    val image = blob("image").nullable()  // Exposed does not have native image, blob is alternative
    val price = integer("price").nullable()
    val proof = short("proof").nullable()
    val categoryId: Column<Int> = reference("category_id", AlcoholCategories.id)
    val styleId: Column<Int> = reference("style_id", AlcoholStyles.id)
    val location = enumerationByName("location", 255, LocationEnum::class).nullable()
    val description = varchar("description", 1000).nullable()
    val like = integer("like").nullable()
    val unlike = integer("unlike").nullable()
    val rating = short("rating").nullable()
    val createdAt = datetime("created_at").nullable()
    val updatedAt = datetime("updated_at").nullable()

    override val primaryKey = PrimaryKey(id)
}
