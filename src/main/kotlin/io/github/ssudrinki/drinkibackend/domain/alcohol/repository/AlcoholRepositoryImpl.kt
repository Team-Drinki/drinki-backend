package io.github.ssudrinki.drinkibackend.domain.alcohol.repository

import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholDetailResponse
import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholListItem
import io.github.ssudrinki.drinkibackend.domain.alcohol.entity.AlcoholEntity
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

import org.springframework.stereotype.Repository
import schema.AlcoholCategories
import schema.AlcoholLocations
import schema.AlcoholStyles
import schema.Alcohols


@Repository
class AlcoholRepositoryImpl : AlcoholRepository {

    override fun findById(alcoholId: Int): AlcoholEntity {

        return AlcoholEntity.findById(alcoholId)
                    ?: throw EntityNotFoundException(EntityID(alcoholId, Alcohols), AlcoholEntity)
    }

    override fun findByIdWithDetails(alcoholId: Int): AlcoholDetailResponse {
        return transaction {
            Alcohols
                    .join(AlcoholCategories, JoinType.LEFT, Alcohols.categoryId, AlcoholCategories.id)
                    .join(AlcoholStyles, JoinType.LEFT, Alcohols.styleId, AlcoholStyles.id)
                    .join(AlcoholLocations, JoinType.LEFT, Alcohols.locationId, AlcoholLocations.id)
                    .selectAll()
                    .where { Alcohols.id eq alcoholId }
                    .map { row ->
                        AlcoholDetailResponse(
                                id          = row[Alcohols.id].value,
                                name        = row[Alcohols.name],
                                proof       = row[Alcohols.proof],
                                image       = row[Alcohols.image_url],
                                description = row[Alcohols.content],
                                rating      = row[Alcohols.rating],
                                wish        = row[Alcohols.wish],
                                category    = row[AlcoholCategories.name],
                                style       = row[AlcoholStyles.name],
                                location    = row[AlcoholLocations.name],
                                isWished    = ,
                                bestNotes   = ,
                                recommendAlcohols =
                        )
                    }.single()
                    ?: throw EntityNotFoundException(EntityID(alcoholId, Alcohols), AlcoholEntity)
        }
    }


//    override fun searchAlcohols(query: String, page: Int, size: Int, sort: String,
//                                category: String, location: String, style: String,
//                                priceMin: Int, priceMax: Int, rating: Double // AlcoholSearchRequest
//    ): List<AlcoholListItem> {
//
//        // EXPOSED
//
//
//        return TODO("Provide the return value");
//    }







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

//    override fun searchAlcohols(query: String, page: Int, size: Int, sort: String,
//                                category: String, location: String, style: String,
//                                priceMin: Int, priceMax: Int, rating: Double // AlcoholSearchRequest
//    ): List<AlcoholListItem> {
//
//        // EXPOSED
//
//
//        return TODO("Provide the return value");
//    }







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