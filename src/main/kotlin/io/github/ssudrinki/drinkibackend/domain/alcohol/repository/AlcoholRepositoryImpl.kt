package io.github.ssudrinki.drinkibackend.domain.alcohol.repository

import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholListItem
import org.springframework.stereotype.Repository
import schema.AlcoholCategories
import schema.AlcoholLocations
import schema.AlcoholStyles
import schema.Alcohols

@Repository
class AlcoholRepositoryImpl : AlcoholRepository {
    override fun searchAlcohols(query: String, page: Int, size: Int, sort: String,
                                category: String, location: String, style: String,
                                priceMin: Int, priceMax: Int, rating: Double
    ): List<AlcoholListItem> {

        return TODO("Provide the return value");
    }

//    override fun findById(id: Int): ResultRow? {
//        TODO("Not yet implemented")
//    }
//
//    override fun create(name: String, proof: Short, categoryId: Int, styleId: Int, locationId: Int, content: String?, price: BigDecimal?, imageUrl: String?): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun update(id: Int, name: String?, proof: Short?, content: String?): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun delete(id: Int): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun getTotalCount(query: String): Long {
//        TODO("Not yet implemented")
//    }


}