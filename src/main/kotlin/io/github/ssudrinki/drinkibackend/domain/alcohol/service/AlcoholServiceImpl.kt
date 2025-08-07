package io.github.ssudrinki.drinkibackend.domain.alcohol.service

import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.request.AlcoholSearchRequest
import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholListResponse
import io.github.ssudrinki.drinkibackend.domain.alcohol.repository.AlcoholRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AlcoholServiceImpl(
        private val alcoholRepository: AlcoholRepository
) : AlcoholService {
    override fun searchAlcohols(request: AlcoholSearchRequest): AlcoholListResponse {
        val alcohols = alcoholRepository.searchAlcohols(
                page = request.page,
                size = request.size,
                sort = request.sort,
                query = request.query,
                category = request.category,
                location = request.location,
                style = request.style,
                priceMin = request.priceMin,
                priceMax = request.priceMax,
                rating = request.rating
        )

        return AlcoholListResponse(
                alcohols = alcohols
        )
    }

//    override fun getAlcoholById(id: Int): AlcoholDetailResponse? {
//        TODO("Not yet implemented")
//    }
//
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