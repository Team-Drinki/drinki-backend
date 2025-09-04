package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.Table

object AlcoholLocations : Table("AlcoholLocations") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)

    override val primaryKey = PrimaryKey(id)
}
