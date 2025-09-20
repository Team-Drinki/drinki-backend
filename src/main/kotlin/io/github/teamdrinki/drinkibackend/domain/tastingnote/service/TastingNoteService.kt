package io.github.teamdrinki.drinkibackend.domain.tastingnote.service

import io.github.ssudrinki.drinkibackend.domain.tastingnote.dto.response.TastingNotesListItem

interface TastingNoteService {
    fun getBestTastingNotesByAlcoholId(alcoholId: Int): List<TastingNotesListItem>
}