package io.github.teamdrinki.drinkibackend.schema

package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime
import schema.AlcoholCategories
import schema.AlcoholLocations
import schema.AlcoholStyles
import schema.TastingNotes
import schema.Users

/**
 * 술 정보를 저장하는 Table
 */
object Alcohols : Table("Alcohols") {
    val id = integer("id").autoIncrement()
    val tastingNoteId = reference("tasting_note_id", TastingNotes.id)
    val userId = reference("user_id", Users.id)
    val name = varchar("name", 255)
    val image_url = text("image_url")
    val price = decimal("price", 20, 5)
    val proof = float("proof")
    val rating = float("rating")
    val wish = integer("wish")
    val content = text("content")
    val categoryId = reference("category_id", AlcoholCategories.id)
    val styleId = reference("style_id", AlcoholStyles.id)
    val locationId = reference("location_id", AlcoholLocations.id)
    val content = text("content")
    val rating = decimal("rating", 5, 2)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}