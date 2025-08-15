package io.github.teamdrinki.drinkibackend.schema

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.datetime.datetime

/**
 * 사용자의 술 위시리스트를 저장하는 Table
 *
 * Exposed DAO 패턴을 사용합니다.
 */
object Wishes : IntIdTable("Wishes") {
    val userId = reference("user_id", Users.id)
    val alcoholId = reference("alcohol_id", Alcohols.id)
    val createdAt = datetime("created_at")
}
