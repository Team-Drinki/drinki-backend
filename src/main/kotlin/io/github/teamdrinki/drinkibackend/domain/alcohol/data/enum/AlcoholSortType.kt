package io.github.teamdrinki.drinkibackend.domain.alcohol.data.enum

import io.github.teamdrinki.drinkibackend.schema.Alcohols
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.SortOrder

/**
 * 정렬 방식을 나타내는 enum
 */
enum class AlcoholSortType(val value: String) {
    CREATED_AT  ("CreatedAt"),
    VIEW        ("View"),
    TASTING_NOTE("TastingNote"),
    LIKE        ("Like"),
    RATING      ("Rating"),
    PRICE_DESC  ("PriceDesc"),
    PRICE_ASC   ("PriceAsc");

    companion object {
        fun fromValue(input: String): AlcoholSortType? {
            return entries.find { it.value == input }
        }

        fun getOrderBy(sort: String): Pair<Column<*>, SortOrder> {
            val sortType = fromValue(sort)
            return when(sortType) {
                VIEW         -> Alcohols.viewCnt   to SortOrder.DESC
                TASTING_NOTE -> Alcohols.noteCnt   to SortOrder.DESC
                LIKE         -> Alcohols.wishCnt   to SortOrder.DESC
                RATING       -> Alcohols.rating    to SortOrder.DESC
                PRICE_DESC   -> Alcohols.price     to SortOrder.DESC
                PRICE_ASC    -> Alcohols.price     to SortOrder.ASC
                else         -> Alcohols.createdAt to SortOrder.DESC  // "CreatedAt" 포함
            }
        }
    }
}