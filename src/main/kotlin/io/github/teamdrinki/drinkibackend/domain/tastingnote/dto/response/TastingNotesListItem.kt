package io.github.teamdrinki.drinkibackend.domain.tastingnote.dto.response

data class TastingNotesListItem (
        val id: Int,
        val name: String,
        val image: String?,
        val category: String,
        val like: Int,
        val viewCnt: Int,
        val commentCnt: Int
)