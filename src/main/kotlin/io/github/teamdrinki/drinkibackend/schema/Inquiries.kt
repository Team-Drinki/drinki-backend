package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime

object Inquiries : Table("inquiries") {
    val id = long("id").autoIncrement()
    val title = varchar("title", 100)
    val content = text("content")
    val answer = text("answer").nullable()
    val isSecret = char("is_secret", 1)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
    val writer = reference("writer", Users.id)

    override val primaryKey = PrimaryKey(id)
}
