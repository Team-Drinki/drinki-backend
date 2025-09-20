package io.github.ssudrinki.drinkibackend.domain.alcohol.service

import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.request.AlcoholSearchRequest
import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholDetailResponse
import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholListResponse
import io.github.ssudrinki.drinkibackend.domain.alcohol.repository.AlcoholRepository
import io.github.ssudrinki.drinkibackend.domain.alcohol.repository.WishRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AlcoholServiceImpl(
        private val alcoholRepository: AlcoholRepository,
        private val wishRepository: WishRepository
) : AlcoholService {

    override fun searchAlcohols(alcoholSearchRequest: AlcoholSearchRequest): AlcoholListResponse {
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

    override fun getAlcoholDetail(alcoholId: Int): AlcoholDetailResponse {
        val alcohol = alcoholRepository.findById(alcoholId);   // 술 상세 정보 조회

        return AlcoholDetailResponse(
                id = alcohol.id.value,
                name = alcohol.name,
                proof = alcohol.proof,
                image = alcohol.imageUrl,
                category = alcohol.category,
                location = alcohol.location,
                style = alcohol.style,
                description = alcohol.description,
                wish = alcohol.wishCount,
                rating = alcohol.averageRating,
                isWished = checkUserWish(alcoholId),
                bestNotes = getBestNotes(alcoholId),
                recommendAlcohols = getRecommendations(alcoholId)
        )
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