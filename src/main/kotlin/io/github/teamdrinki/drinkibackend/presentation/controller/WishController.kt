package io.github.teamdrinki.drinkibackend.presentation.controller

import io.github.ssudrinki.drinkibackend.domain.alcohol.service.WishService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/wishes")
class WishController (
        private val wishService: WishService
) {
    /**
     * 술을 위시리스트에 추가합니다.
     *
     * 로그인한 사용자의 위시리스트에 특정 술을 추가합니다.
     *
     * @param id 위시리스트에 추가할 술의 ID
     * @throws WishAlreadyException 위시리스트에 있는 경우 404 반환
     * @throws AlcoholNotFoundException 존재하지 않는 술인 경우 404 반환
     *
     * @example POST /api/v1/alcohols/123/wishes
     */
    @PostMapping("/{id}/wishes")
    fun addWish(
            @PathVariable id: Int
    ): ResponseEntity<Unit>{
        val userId = 1L;

        val response = wishService.addWish(userId, id)
        return ResponseEntity.status(HttpStatus.CREATED).build()    // 201 Created
    }

    /**
     * 술을 위시리스트에서 제거합니다.
     *
     * 로그인한 사용자의 위시리스트에서 특정 술을 제거합니다.
     *
     * @param id 위시리스트에서 제거할 술의 ID
     * @throws WishNotFoundException 위시리스트에 없는 경우 404 반환
     * @throws AlcoholNotFoundException 존재하지 않는 술인 경우 404 반환
     *
     * @example DELETE /api/v1/alcohols/123/wishes
     */
    @DeleteMapping("/{id}/wishes")
    fun deleteWish(
            @PathVariable id: Int
    ): ResponseEntity<Unit>{
        val userId = 1L;

        val response = wishService.removeWish(userId, id)
        return ResponseEntity.noContent().build()   //204 No Content
    }
}
