package io.github.teamdrinki.drinkibackend.presentation.controller

import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.request.TastingNoteListRequest
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteListResponse
import io.github.teamdrinki.drinkibackend.domain.tastingnote.service.TastingNoteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 테이스팅 노트 관련 API를 제공하는 컨트롤러
 */
@RestController
@RequestMapping("/api/v1/notes")
class TastingNoteController(
    private val tastingNoteService: TastingNoteService,
) {
    /**
     * 테이스팅 노트 목록을 조회합니다.
     *
     * 다양한 조건(검색어, 카테고리)으로 테이스팅 노트를 검색할 수 있으며,
     * 페이징과 정렬을 지원합니다.
     *
     * @param request 검색 조건 (검색어, 카테고리, 페이지, 크기, 정렬)
     * @return 검색된 테이스팅 노트 목록
     *
     * @example GET /api/v1/notes?query="라가불린"&category="위스키"&page=1&size=10&sort="createdAt"
     */
    @GetMapping("/")
    fun getTastingNoteList(
        request: TastingNoteListRequest
    ): ResponseEntity<TastingNoteListResponse> {
        return tastingNoteService.getTastingNoteList(request)
    }

    /**
     * 특정 테이스팅 노트의 상세 정보를 조회합니다.
     *
     * @param noteId 조회할 테이스팅 노트의 ID
     * @return 테이스팅 노트의 상세 정보
     *
     * @example GET /api/v1/notes/{noteId}
     */
    @GetMapping("/{noteId}")
    fun getTastingNote(
        @PathVariable noteId: Long
    ) : ResponseEntity<*> {
        return tastingNoteService.getTastingNote(noteId)
    }
//
//    @PostMapping("/")
//    fun createTastingNote(
//    ): ResponseEntity<*> {
//        return tastingNoteService.createTastingNote(request)
//    }

    /**
     * 특정 테이스팅 노트를 삭제합니다.
     *
     * @param noteId 삭제할 테이스팅 노트의 ID
     * @return 삭제 결과 응답
     *
     * @example DELETE /api/v1/notes/{noteId}
     */
    @DeleteMapping("/{noteId}")
    fun deleteTastingNote(
        @PathVariable noteId: Long
    ): ResponseEntity<*> {
        return tastingNoteService.deleteTastingNote(noteId)
    }

}