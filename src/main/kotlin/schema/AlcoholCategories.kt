package schema

import org.jetbrains.exposed.v1.core.Table

object AlcoholCategories : Table("AlcoholCategories") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255).nullable()

    override val primaryKey = PrimaryKey(id)
}
