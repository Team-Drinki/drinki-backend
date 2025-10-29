package io.github.teamdrinki.drinkibackend.domain.tastingnote.service

import io.github.teamdrinki.drinkibackend.common.util.PageUtil
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.request.TastingNoteListRequest
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteListItem
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteListResponse
import io.github.teamdrinki.drinkibackend.domain.tastingnote.repository.TastingNoteRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TastingNoteServiceImpl(
    private val tastingNoteRepository: TastingNoteRepository
) : TastingNoteService {

    override fun getBestTastingNotesByAlcoholId(alcoholId: Int): List<TastingNoteListItem> {
        TODO("Not yet implemented")
    }

    override fun getTastingNoteList(request: TastingNoteListRequest): ResponseEntity<TastingNoteListResponse> {
        // 디비에서 필터로 검색
        val pagedListResult = tastingNoteRepository.findAllByFilters(
            query = request.query,
            category = request.category,
            page = request.page,
            size = request.size,
            sort = request.sort,
        )

        // PageUtil 생성 - totalCnt 사용
        val pageUtil = PageUtil.of(
            page = request.page,
            size = request.size,
            totalCount = pagedListResult.totalCnt
        )

        return ResponseEntity.ok(
            TastingNoteListResponse(
                notes = pagedListResult.content,
                pageUtil = pageUtil
            )
        )
    }
}
