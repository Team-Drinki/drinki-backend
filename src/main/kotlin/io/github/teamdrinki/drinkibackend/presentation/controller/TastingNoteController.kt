package io.github.teamdrinki.drinkibackend.presentation.controller

import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.request.TastingNoteListRequest
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteListResponse
import io.github.teamdrinki.drinkibackend.domain.tastingnote.service.TastingNoteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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




//    @GetMapping("/{noteId}")
//    fun getTastingNote() : ResponseEntity<TastingNoteDetailResponse> {
//
//    }
}