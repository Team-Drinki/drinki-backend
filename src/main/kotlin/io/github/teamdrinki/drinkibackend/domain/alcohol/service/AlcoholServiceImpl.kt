package io.github.teamdrinki.drinkibackend.domain.alcohol.service

import io.github.teamdrinki.drinkibackend.common.util.PageUtil
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.request.AlcoholRecommendRequest
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.request.AlcoholSearchRequest
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholDetailResponse
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholListItem
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholListResponse
import io.github.teamdrinki.drinkibackend.domain.alcohol.repository.AlcoholRepository
import io.github.teamdrinki.drinkibackend.domain.tastingnote.service.TastingNoteService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.Int

@Service
@Transactional(readOnly = true)
class AlcoholServiceImpl(
        private val alcoholRepository: AlcoholRepository,
        private val wishService: WishService,
        private val tastingNoteService: TastingNoteService
) : AlcoholService {

    override fun searchAlcoholList(alcoholSearchRequest: AlcoholSearchRequest): AlcoholListResponse {
        val pagedListResult  = alcoholRepository.findListByFilters(
            page     = alcoholSearchRequest.page,
            size     = alcoholSearchRequest.size,
            sort     = alcoholSearchRequest.sort,
            query    = alcoholSearchRequest.query,
            category = alcoholSearchRequest.category,
            location = alcoholSearchRequest.location,
            style    = alcoholSearchRequest.style,
            priceMin = alcoholSearchRequest.priceMin,
            priceMax = alcoholSearchRequest.priceMax,
            rating   = alcoholSearchRequest.rating
        )

        // PagedListResult에서 content 추출하여 Entity -> ListItem 변환
        val alcoholListItems = pagedListResult.content.map { entity ->
            AlcoholListItem(
                id       = entity.id.value,
                name     = entity.name,
                image    = entity.imageUrl,
                category = entity.category.name,
                wish     = entity.wish,
                rating   = entity.rating,
                viewCnt  = entity.viewCnt,
                noteCnt  = entity.noteCnt
            )
        }

        // PageUtil 생성 - totalCnt 사용
        val pageUtil = PageUtil.of(
            page       = alcoholSearchRequest.page,
            size       = alcoholSearchRequest.size,
            totalCount = pagedListResult.totalCnt
        )

        return AlcoholListResponse(
            items    = alcoholListItems,
            pageUtil = pageUtil
        )
    }

    override fun getAlcoholDetail(userId: Long, alcoholId: Int): AlcoholDetailResponse {
        val alcohol = alcoholRepository.findById(alcoholId)

        return AlcoholDetailResponse(
            id          = alcohol.id.value,
            name        = alcohol.name,
            proof       = alcohol.proof,
            image       = alcohol.imageUrl,
            rating      = alcohol.rating,
            wish        = alcohol.wish,
            description = alcohol.content,
            category    = alcohol.category.name,
            location    = alcohol.location.name,
            style       = alcohol.style.name,
        )
    }

    override fun recommendAlcohols(alcoholRecommendRequest: AlcoholRecommendRequest): AlcoholListResponse {

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