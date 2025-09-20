package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.sql.Table

object AlcoholCategories : Table("AlcoholCategories") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)

    override val primaryKey = PrimaryKey(id)
}
