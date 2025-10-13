package io.github.teamdrinki.drinkibackend.domain.alcohol.repository

import io.github.teamdrinki.drinkibackend.common.dto.PagedListResult
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.enum.AlcoholSortType
import io.github.teamdrinki.drinkibackend.schema.AlcoholCategories
import io.github.teamdrinki.drinkibackend.schema.AlcoholLocations
import io.github.teamdrinki.drinkibackend.schema.AlcoholStyles
import io.github.teamdrinki.drinkibackend.schema.Alcohols
import io.github.teamdrinki.drinkibackend.schema.Wish
import io.github.teamdrinki.drinkibackend.schema.Wishes
import io.github.teamdrinki.drinkibackend.schema.Alcohol
import org.jetbrains.exposed.v1.core.Op
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.like
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
class AlcoholRepositoryImpl : AlcoholRepository {

    override fun findById(alcoholId: Int): Alcohol {
        return transaction{
            Alcohol.findById(alcoholId)
                ?: throw NoSuchElementException("Alcohol not found with id: $alcoholId")
        }
    }

    override fun findAllByFilters(
        userId: Long?,
        page: Int, size: Int, sort: String,
        query: String, category: String, location: String, style: String, priceMin: Int, priceMax: Int, rating: Double
    ): PagedListResult<Alcohol> {
        val offset = ((page - 1) * size)
        var condition: Op<Boolean> = Op.TRUE

        if (query.isNotBlank()) {   // 검색어
            condition = condition and (Alcohols.name like "%$query%")
        }

        if (category.isNotBlank()) { // 카테고리
            condition = condition and (AlcoholCategories.name eq category)
        }

        if (location.isNotBlank()) { // 지역
            condition = condition and (AlcoholLocations.name eq location)
        }

        if (style.isNotBlank()) {   // 스타일
            condition = condition and (AlcoholStyles.name eq style)
        }

        if (priceMin > 0 || priceMax < 1000000000) { // 가격
            condition = condition and (Alcohols.price greaterEq BigDecimal(priceMin)) and
                    (Alcohols.price lessEq BigDecimal(priceMax))
        }

        if (rating > 0.0) {     // 평점
            condition = condition and (Alcohols.rating greaterEq BigDecimal(rating))
        }

        return transaction {
            val query = Alcohol.find { condition }

            // 전체 개수 조회
            val totalCnt = query.count()
            val entities = query
                .orderBy(AlcoholSortType.getOrderBy(sort))
                .drop(offset)
                .take(size)
                .toList()

            // eager loading으로 wishes 미리 로드 (N+1 방지)
            if (userId != null && entities.isNotEmpty()) {
                val alcoholIds = entities.map { it.id }
                Wish.find {
                    Wishes.alcoholId inList alcoholIds
                }.toList() // 모든 wishes를 한 번에 로드
            }

            PagedListResult(entities, totalCnt)
        }
    }

    override fun findAllByOrderByViewCntDesc(
        page: Int, size: Int
    ): PagedListResult<Alcohol> {
        return transaction {

            val totalCnt = Alcohol.all().count()

            val entities = Alcohol
                .all()
                .orderBy(AlcoholSortType.getOrderBy("View"))
                .take(size)
                .toList()

            PagedListResult(entities, totalCnt)
        }
    }

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

}