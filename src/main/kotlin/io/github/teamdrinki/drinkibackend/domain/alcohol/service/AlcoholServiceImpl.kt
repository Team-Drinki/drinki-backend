package io.github.teamdrinki.drinkibackend.domain.alcohol.service

import io.github.ssudrinki.drinkibackend.domain.alcohol.data.dto.AlcoholDetailDto
import io.github.ssudrinki.drinkibackend.domain.alcohol.data.request.AlcoholSearchRequest
import io.github.ssudrinki.drinkibackend.domain.alcohol.data.response.AlcoholDetailResponse
import io.github.ssudrinki.drinkibackend.domain.alcohol.data.response.AlcoholListItem
import io.github.ssudrinki.drinkibackend.domain.alcohol.repository.AlcoholRepository
import io.github.ssudrinki.drinkibackend.domain.tastingnote.service.TastingNoteService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AlcoholServiceImpl(
        private val alcoholRepository: AlcoholRepository,
        private val wishService: WishService,
        private val tastingNoteService: TastingNoteService
) : AlcoholService {

    override fun searchAlcohols(alcoholSearchRequest: AlcoholSearchRequest): List<AlcoholListItem> {
        val alcohols = alcoholRepository.searchAlcohols(
                page = alcoholSearchRequest.page,
                size = alcoholSearchRequest.size,
                sort = alcoholSearchRequest.sort,
                query = alcoholSearchRequest.query,
                category = alcoholSearchRequest.category,
                location = alcoholSearchRequest.location,
                style = alcoholSearchRequest.style,
                priceMin = alcoholSearchRequest.priceMin,
                priceMax = alcoholSearchRequest.priceMax,
                rating = alcoholSearchRequest.rating
        )

        return AlcoholListResponse(
                alcohols = alcohols
        )
    }

    override fun getAlcoholDetail(userId: Long, alcoholId: Int): AlcoholDetailDto {
        val alcohol         = alcoholRepository.findById(alcoholId)                        // 술 상세 정보 조회



        val bestTastingNote = tastingNoteService.getBestTastingNotesByAlcoholId(alcoholId) // 술 테이스팅 노트 조회
        val isWished        = wishService.isWished(userId, alcoholId)                      // 사용자의 위시 여부

        return AlcoholDetailDto(
                id = alcohol.id,
                name = alcohol.name,
                proof = alcohol.proof,
                image = alcohol.imageUrl,
                category = alcohol.category,
                location = alcohol.location,
                style = alcohol.style,
                description = alcohol.content,
                wish = alcohol.wishCount,
                rating = alcohol.averageRating,
                isWished = isWished,
        )
    }

    override fun recommendAlcohols(): List<AlcoholListItem> {
        TODO("Not yet implemented")
    }


//    override fun createAlcohol(request: AlcoholCreateRequest): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun updateAlcohol(id: Int, name: String?, proof: Short?, content: String?): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun deleteAlcohol(id: Int): Boolean {
//        TODO("Not yet implemented")
//    }


}