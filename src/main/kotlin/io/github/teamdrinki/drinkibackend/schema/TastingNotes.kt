package io.github.teamdrinki.drinkibackend.schema

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.datetime.datetime
import org.jetbrains.exposed.v1.json.jsonb
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

@Serializable
data class Note(val name: String) // TODO: replace with real note format

val format = Json { ignoreUnknownKeys = true; prettyPrint = true }

object TastingNotes : IntIdTable("TastingNotes") {
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
}

class TastingNote(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TastingNote>(TastingNotes)

    var alcoholId by TastingNotes.alcoholId
    var userId by TastingNotes.userId
    var commentId by TastingNotes.commentId
    var title by TastingNotes.title
    var image_url by TastingNotes.image_url
    var aromaNote by TastingNotes.aromaNote
    var palateNote by TastingNotes.palateNote
    var finishNote by TastingNotes.finishNote
    var createdAt by TastingNotes.createdAt
    var updatedAt by TastingNotes.updatedAt
}
