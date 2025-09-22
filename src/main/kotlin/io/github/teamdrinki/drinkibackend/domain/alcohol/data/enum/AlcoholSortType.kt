package io.github.teamdrinki.drinkibackend.domain.alcohol.data.enum

/**
 * 정렬 방식을 나타내는 enum
 */
enum class SortType(val value: String) {
    CREATED_AT("CreatedAt"),
    VIEW("View"),
    TASTING_NOTE("TastingNote"),
    LIKE("Like"),
    RATING("Rating"),
    PRICE_DESC("PriceDesc"),
    PRICE_ASC("PriceAsc");

    companion object {
        fun fromValue(value: String): SortType? {
            return entries.find { it.value == value }
        }
    }
}