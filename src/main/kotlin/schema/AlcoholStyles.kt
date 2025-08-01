package schema

import org.jetbrains.exposed.v1.core.Table

object AlcoholStyles : Table("AlcoholStyles") {
    val id = integer("id").autoIncrement()
    val categoryId = reference("category_id", AlcoholCategories.id)
    val name = varchar("name", 255) // not null

    override val primaryKey = PrimaryKey(id)
}
