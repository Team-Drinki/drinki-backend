package schema

import org.jetbrains.exposed.v1.core.Table

object FlavorKeywords : Table("FlavorKeywords") {
    val id = integer("id").autoIncrement()
    val categoryId = reference("category_id", FlavorCategories.id)
    val name = varchar("name", 255)

    override val primaryKey = PrimaryKey(id)
}
