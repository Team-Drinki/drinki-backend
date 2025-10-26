package io.github.teamdrinki.drinkibackend.domain.user.service

import io.github.teamdrinki.drinkibackend.common.util.PageUtil
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.request.WishListRequest
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholListItem
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholListResponse
import io.github.teamdrinki.drinkibackend.domain.alcohol.repository.WishRepository
import io.github.teamdrinki.drinkibackend.domain.user.data.request.UserProfileUpdateRequest
import io.github.teamdrinki.drinkibackend.domain.user.data.response.UserProfileResponse
import io.github.teamdrinki.drinkibackend.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val wishRepository: WishRepository,
) : UserService {

    override fun getUserProfile(
        userId: Long
    ): UserProfileResponse {
        val user = userRepository.findById(userId)

        return UserProfileResponse(
            socialType = user.socialType,
            nickname   = user.nickname,
            createdAt  = user.createdAt
        )
    }

    override fun updateUserProfile(
        userId: Long, request: UserProfileUpdateRequest
    ){
        userRepository.updateById(userId) { entity ->
            entity.nickname = request.nickname
            entity.profileImageUrl = request.profileImageURL
        }
    }

    override fun getUserWishList(
        userId: Long,
        request: WishListRequest
    ): AlcoholListResponse {
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