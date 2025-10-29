package io.github.teamdrinki.drinkibackend.domain.tastingnote.data.request

data class TastingNoteListRequest(
    val query: String?,
    val category: String?,
    val page: Int,
    val size: Int,
    val sort: String
)
