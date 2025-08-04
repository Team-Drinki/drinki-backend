package schema

import org.jetbrains.exposed.v1.core.Table

object FlavorCategories : Table("FlavorCategories") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)

    override val primaryKey = PrimaryKey(id)
}
