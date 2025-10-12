package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.datetime.datetime

/**
 * 술 정보를 저장하는 Table
 *
 * Exposed DAO 패턴을 사용합니다.
 */
object Alcohols : IntIdTable("Alcohols") {
    val userId     = reference("user_id", Users)
    val name       = varchar("name", 255)
    val imageUrl   = text("image_url")
    val price      = decimal("price", 20, 5)
    val proof      = float("proof")
    val rating     = decimal("rating", 3, 2)                // 5점 만점 (0.0 ~ 5.0)
    val wishCnt    = integer("wish_cnt")
    val viewCnt    = integer("view_cnt")
    val noteCnt    = integer("note_cnt")
    val content    = text("content")
    val category = reference("category_id", AlcoholCategories)
    val style    = reference("style_id", AlcoholStyles)
    val location = reference("location_id", AlcoholLocations)

    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}