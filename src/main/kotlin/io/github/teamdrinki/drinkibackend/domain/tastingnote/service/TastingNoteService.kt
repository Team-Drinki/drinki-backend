package io.github.teamdrinki.drinkibackend.domain.tastingnote.service

import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.request.TastingNoteListRequest
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteDetailResponse
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteListItem
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteListResponse
import org.springframework.http.ResponseEntity

interface TastingNoteService {
    fun getBestTastingNotesByAlcoholId(alcoholId: Int): List<TastingNoteListItem>
    fun getTastingNoteList(request: TastingNoteListRequest): ResponseEntity<TastingNoteListResponse>
    fun getTastingNote(noteId: Long): ResponseEntity<*>
    fun deleteTastingNote(noteId: Long): ResponseEntity<*>
}