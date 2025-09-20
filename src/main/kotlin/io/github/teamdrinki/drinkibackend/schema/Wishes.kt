package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime
import schema.Alcohols
import schema.Users

/**
 * 사용자의 술 위시리스트를 저장하는 Table
 */
object Wishes : Table("Wishes") {
    val id = integer("id").autoIncrement()
    val userId = reference("user_id", Users.id)
    val alcoholId = reference("alcohol_id", Alcohols.id)
    val createdAt = datetime("created_at")

    override val primaryKey = PrimaryKey(id)
}