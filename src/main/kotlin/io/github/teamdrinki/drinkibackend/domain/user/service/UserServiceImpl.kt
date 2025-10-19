package io.github.teamdrinki.drinkibackend.domain.user.service

import io.github.teamdrinki.drinkibackend.domain.alcohol.data.request.WishListRequest
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholListResponse
import io.github.teamdrinki.drinkibackend.domain.user.data.request.UserProfileUpdateRequest
import io.github.teamdrinki.drinkibackend.domain.user.data.response.UserProfileResponse
import io.github.teamdrinki.drinkibackend.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    private val userRepository: UserRepository,
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
        TODO("Not yet implemented")
    }

}