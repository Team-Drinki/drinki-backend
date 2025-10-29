package io.github.teamdrinki.drinkibackend.domain.tastingnote.repository

import io.github.teamdrinki.drinkibackend.common.dto.PagedListResult
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteDetailResponse
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteListItem
import io.github.teamdrinki.drinkibackend.schema.entity.TastingNoteEntity

/**
 * 테이스팅 노트를 관리하는 Repository 인터페이스
 *
 * 이 인터페이스는 테이스팅 노트에 대한 CRUD 작업과
 * 검색, 필터링 기능 등을 제공합니다.
 */
interface TastingNoteRepository {

    /**
     * 검색 조건에 맞는 테이스팅 노트 목록을 조회합니다.
     *
     * @param query 검색 키워드
     * @param category 카테고리 필터
     * @param page 페이지 번호 (1부터 시작)
     * @param size 페이지 크기
     * @param sort 정렬 방식
     *
     * @return 검색된 테이스팅 노트 목록, 총 항목 개수
     */
    fun findAllByFilters(
        query: String?,
        category: String?,
        page: Int,
        size: Int,
        sort: String
    ): PagedListResult<TastingNoteListItem>

    fun findDetailById(noteId: Long): TastingNoteDetailResponse?

    fun findById(noteId: Long): TastingNoteEntity?

    fun delete(note: TastingNoteEntity)
}