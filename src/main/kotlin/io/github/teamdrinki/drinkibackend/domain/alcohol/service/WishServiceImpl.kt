package io.github.teamdrinki.drinkibackend.domain.alcohol.service

import io.github.ssudrinki.drinkibackend.domain.alcohol.data.dao.WishDto
import io.github.ssudrinki.drinkibackend.domain.alcohol.repository.WishRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WishServiceImpl (
        private val wishRepository: WishRepository
) : WishService {
    override fun addWish(userId: Long, alcoholId: Int) {
        wishRepository.create(userId, alcoholId)
    }

    override fun removeWish(userId: Long, alcoholId: Int) {
        wishRepository.delete(userId, alcoholId)
    }

    override fun isWished(userId: Long, alcoholId: Int): Boolean {
        val wishDto = wishRepository.findByUserIdAndAlcoholId( // 이미 위시되어 있는지 확인
                userId, alcoholId
        )

        return (wishDto != null)                               // null 이 아니면 true, null 이면 false
    }

    override fun getWishList(userId: Long): List<WishDto> {
        return wishRepository.findByUserId(userId)
    }
}