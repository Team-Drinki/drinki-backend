package io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response

import io.github.teamdrinki.drinkibackend.common.util.PageUtil

data class TastingNoteListResponse(
    val notes: List<TastingNoteListItem>,
    val pageUtil: PageUtil
)
