package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * 술 정보를 저장하는 Table
 *
 * Exposed DAO 패천을 사용합니다.
 */
object Alcohols : IntIdTable("Alcohols") {
    val tastingNoteId = reference("tasting_note_id", TastingNotes.id)
    val userId = reference("user_id", Users.id)
    val name = varchar("name", 255)
    val image_url = text("image_url")
    val price = decimal("price", 5, 15)
    val proof = float("proof")
    val rating = float("rating")
    val wish = integer("wish")
    val content = text("content")
    val categoryId = reference("category_id", AlcoholCategories.id)
    val styleId = reference("style_id", AlcoholStyles.id)
    val locationId = reference("location_id", AlcoholLocations.id)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
