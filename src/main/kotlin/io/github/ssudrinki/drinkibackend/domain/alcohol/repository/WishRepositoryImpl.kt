package io.github.ssudrinki.drinkibackend.domain.alcohol.repository

import org.springframework.stereotype.Repository

@Repository
class WishRepositoryImpl : WishRepository {
    override fun findByUserIdAndAlcoholId(): AlcoholWishResponse {
//        TODO("Not yet implemented")
    }

    override fun findById(id: Int): AlcoholWishResponse {
//        TODO("Not yet implemented")
    }
}