package schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.Column

object AlcoholStyles : Table("AlcoholStyles") {
    val id = integer("id").autoIncrement()
    val categoryId: Column<Int> = reference("category_id", AlcoholCategories.id)
    val name = varchar("name", 255)

    override val primaryKey = PrimaryKey(id)
}
