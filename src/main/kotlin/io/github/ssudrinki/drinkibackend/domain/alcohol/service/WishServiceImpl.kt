package io.github.ssudrinki.drinkibackend.domain.alcohol.service

import io.github.ssudrinki.drinkibackend.domain.alcohol.repository.WishRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WishServiceImpl (
        private val wishRepository: WishRepository
) : WishService {
    override fun addWish(alcoholId: Int) {
        //val currentUser = getCurrentUser()    // 사용자 획인
        val userId = 1;

        val existingWish =

        if(isWished(userId, alcoholId)){

        }

        wishRepository.create(currentUser.id, alcoholId)

    }

    override fun removeWish(alcoholId: Int) {
        //val currentUser = getCurrentUser()    // 사용자 획인
        val userId = 1;

        val existingWish = wishRepository.findByUserIdAndAlcoholId(currentUser.id, alcoholId)
                ?: throw WishNotFoundException("위시리스트에 없는 술입니다.")

        wishRepository.delete(existingWish)

    }

    override fun isWished(userId: Long, alcoholId: Int): Boolean {
        val wishDto = wishRepository.findByUserIdAndAlcoholId( // 이미 위시되어 있는지 확인
                userId, alcoholId
        )

        return (wishDto != null)                               // null 이 아니면 true, null 이면 false
    }

    override fun getWishList(alcoholId: Int, userId: Int) {
        TODO("Not yet implemented")
    }
}