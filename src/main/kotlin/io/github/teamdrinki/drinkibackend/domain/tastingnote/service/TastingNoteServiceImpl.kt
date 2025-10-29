package io.github.teamdrinki.drinkibackend.domain.tastingnote.service

import io.github.teamdrinki.drinkibackend.common.exception.ErrorResponse
import io.github.teamdrinki.drinkibackend.common.util.PageUtil
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.request.TastingNoteListRequest
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteListItem
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteListResponse
import io.github.teamdrinki.drinkibackend.domain.tastingnote.repository.TastingNoteRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
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

    override fun getTastingNote(noteId: Long): ResponseEntity<*> {
        // DB에서 noteId로 테이스팅 노트 조회
        val result = tastingNoteRepository.findById(noteId)
            ?: run {
                val errorResponse = ErrorResponse("존재하지 않는 테이스팅 노트입니다.")
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
            }
        return ResponseEntity.ok(result)
    }

    override fun deleteTastingNote(noteId: Long): ResponseEntity<*> {
        // 사용자 정보 가져오기
        val userId = SecurityContextHolder.getContext().authentication.principal as Long


        // DB에서 noteId로 테이스팅 노트 조회
        val note = tastingNoteRepository.findById(noteId)
            ?: run {
                val errorResponse = ErrorResponse("존재하지 않는 테이스팅 노트입니다.")
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
            }

        // 현재 사용자와 노트 작성자가 동일한지 확인
        if (note.userId.value != userId) {
            val errorResponse = ErrorResponse("삭제 권한이 없습니다.")
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse)
        }

        tastingNoteRepository.delete(note)

        return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
    }
}
