package io.github.teamdrinki.drinkibackend.domain.alcohol.service

import io.github.teamdrinki.drinkibackend.common.util.PageUtil
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.request.WishListRequest
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholListItem
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholListResponse
import io.github.teamdrinki.drinkibackend.domain.alcohol.repository.WishRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import kotlin.Int

@Service
class WishServiceImpl (
        private val wishRepository: WishRepository
) : WishService {

    @Transactional(readOnly = false)
    override fun addWish(userId: Long, alcoholId: Int) {
        wishRepository.create(userId, alcoholId)
    }

    @Transactional(readOnly = false)
    override fun removeWish(userId: Long, alcoholId: Int) {
        wishRepository.delete(userId, alcoholId)
    }

    @Transactional(readOnly = true)
    override fun isWished(userId: Long, alcoholId: Int): Boolean {
        val wishDto = wishRepository.findByUserIdAndAlcoholId( // 이미 위시되어 있는지 확인
                userId, alcoholId
        )

        return (wishDto != null)                               // null 이 아니면 true, null 이면 false
    }

    @Transactional(readOnly = true)
    override fun getWishList(userId: Long, request: WishListRequest): AlcoholListResponse {
        val pagedListResult = wishRepository.findByUserId(
            request.page,
            request.size,
            request.sort,
            userId)

        // PagedListResult에서 content 추출하여 Entity -> ListItem 변환
        val alcoholItems = pagedListResult.content.map { alcohol ->
            AlcoholListItem(
                id       = alcohol.id.value,
                name     = alcohol.name,
                image    = alcohol.imageUrl,
                category = alcohol.category.toString(),
                wish     = alcohol.wishCount,
                rating   = alcohol.rating,
                viewCnt  = alcohol.viewCnt,
                noteCnt  = alcohol.noteCnt,
                isWish  = true
            )
        }

        // PageUtil 생성 - totalCnt 사용
        val pageUtil = PageUtil.of(
            page       = request.page,
            size       = request.size,
            totalCount = pagedListResult.totalCnt
        )

        return AlcoholListResponse(
            items    = alcoholItems,
            pageUtil = pageUtil
        )
    }
}