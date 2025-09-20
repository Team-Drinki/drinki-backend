package io.github.teamdrinki.drinkibackend.domain.alcohol.repository

import io.github.ssudrinki.drinkibackend.domain.alcohol.data.dao.AlcoholDao
import io.github.teamdrinki.drinkibackend.schema.Alcohols
import org.jetbrains.exposed.v1.core.JoinType
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

import org.springframework.stereotype.Repository
import schema.AlcoholCategories
import schema.AlcoholLocations
import schema.AlcoholStyles
import schema.Users

@Repository
class AlcoholRepositoryImpl : AlcoholRepository {

    override fun findById(alcoholId: Int): AlcoholDao {
        return transaction {
            Alcohols
                .join(Users,             JoinType.LEFT, Alcohols.userId,     Users.id)
                .join(AlcoholCategories, JoinType.LEFT, Alcohols.categoryId, AlcoholCategories.id)
                .join(AlcoholStyles,     JoinType.LEFT, Alcohols.styleId,    AlcoholStyles.id)
                .join(AlcoholLocations,  JoinType.LEFT, Alcohols.locationId, AlcoholLocations.id)
                .selectAll()
                .where { Alcohols.id eq alcoholId }
                .singleOrNull()?.let { row ->
                    AlcoholDao(
                        id        = row[Alcohols.id],
                        name      = row[Users.nickname],
                        imageUrl  = row[Alcohols.image_url],
                        price     = row[Alcohols.price],
                        proof     = row[Alcohols.proof],
                        rating    = row[Alcohols.rating],
                        wish      = row[Alcohols.wish],
                        content   = row[Alcohols.content],
                        category  = row[AlcoholCategories.name],
                        style     = row[AlcoholStyles.name],
                        location  = row[AlcoholLocations.name],
                        createdAt = row[Alcohols.createdAt],
                        updatedAt = row[Alcohols.updatedAt]
                    )
                } ?: throw Exception("Alcohol not found")
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