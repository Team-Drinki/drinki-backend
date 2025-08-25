package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime

enum class AlcoholRequestStatus { approve, pending, reject }

object AlcoholRequests : Table("AlcoholRequests") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val proof = short("proof")
    val categoryId = reference("category_id", AlcoholCategories.id)
    val styleId = reference("style_id", AlcoholStyles.id)
    val locationId = reference("location_id", AlcoholLocations.id).nullable()
    val userId = reference("user_id", Users.id)
    val image_url = text("image_url").nullable()
    val status = enumerationByName("status", 20, AlcoholRequestStatus::class)
    val content = text("content").nullable()
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}