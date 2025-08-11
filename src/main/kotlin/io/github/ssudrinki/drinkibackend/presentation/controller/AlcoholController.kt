package io.github.ssudrinki.drinkibackend.presentation.controller

import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.request.AlcoholSearchRequest
import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholDetailResponse
import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholListResponse
import io.github.ssudrinki.drinkibackend.domain.alcohol.service.AlcoholService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/alcohols")
class AlcoholController(
        private val alcoholService: AlcoholService
) {

    @GetMapping("/search")
    fun getAlcoholList(
            @Valid request: AlcoholSearchRequest
    ): ResponseEntity<AlcoholListResponse> {
        val response = alcoholService.searchAlcohols(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getAlcohol(
            @PathVariable id: Int
    ): ResponseEntity<AlcoholDetailResponse>{
        val response = alcoholService.getAlcoholDetail(id)
        return ResponseEntity.ok(response)
    }


}

