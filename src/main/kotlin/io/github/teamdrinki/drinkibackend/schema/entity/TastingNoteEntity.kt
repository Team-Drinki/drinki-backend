package io.github.teamdrinki.drinkibackend.schema.entity

import io.github.teamdrinki.drinkibackend.schema.TastingNotes
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class TastingNoteEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TastingNoteEntity>(TastingNotes)

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
