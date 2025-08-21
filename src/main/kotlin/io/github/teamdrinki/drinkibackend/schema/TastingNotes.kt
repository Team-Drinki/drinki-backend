package io.github.teamdrinki.drinkibackend.schema

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime
import org.jetbrains.exposed.v1.json.jsonb

@Serializable
data class Note(val name: String) // TODO: replace with real note format

val format = Json { ignoreUnknownKeys = true; prettyPrint = true }

object TastingNotes : Table("TastingNotes") {
    val id = integer("id").autoIncrement()
    val alcoholId = reference("alcohol_id", Alcohols.id)
    val userId = reference("user_id", Users.id)
    val commentId = reference("comment_id", Comments.id)
    val title = varchar("title", 255)
    val image_url = text("image_url").nullable()
    val aromaNote = jsonb<Note>("aroma_note", format)
    val palateNote = jsonb<Note>("palate_note", format)
    val finishNote = jsonb<Note>("finish_note", format)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}