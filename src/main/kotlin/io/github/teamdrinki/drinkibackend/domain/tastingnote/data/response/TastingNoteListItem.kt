package io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response

data class TastingNoteListItem (
    val noteId: Int,
    val noteTitle: String,
    val alcoholCategory: String,
    val alcoholName: String,
    val noteImage: String,
    val writer: String,
    val commentNum: Int,
    val like: Int,
    val unlike: Int,
    val viewer: Int,
    val createdTime: String
)