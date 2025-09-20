package io.github.teamdrinki.drinkibackend.domain.tastingnote.service

import io.github.ssudrinki.drinkibackend.domain.tastingnote.dto.response.TastingNotesListItem
import io.github.ssudrinki.drinkibackend.domain.tastingnote.repository.TastingNoteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TastingNoteServiceImpl(
    private val tastingNoteRepository: TastingNoteRepository
) : TastingNoteService {

    override fun getBestTastingNotesByAlcoholId(alcoholId: Int): List<TastingNotesListItem> {
        TODO("Not yet implemented")
    }
}
