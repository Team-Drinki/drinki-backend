package io.github.teamdrinki.drinkibackend.domain.alcohol.repository

import io.github.ssudrinki.drinkibackend.domain.alcohol.data.dao.WishDto
import io.github.teamdrinki.drinkibackend.schema.Wishes
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.v1.core.JoinType
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.springframework.stereotype.Repository
import schema.Users

@Repository
class WishRepositoryImpl : WishRepository {

    override fun findByUserId(userId: Long): List<WishDto> {
        return transaction {
            Wishes
                .selectAll()
                    .where{
                        (Wishes.userId eq userId)
                    }
                    .map { row ->
                        WishDto(
                                id = row[Wishes.id],
                                userId = row[Wishes.userId],
                                alcoholId = row[Wishes.alcoholId],
                                createdAt = row[Wishes.createdAt]
                        )
                    }
        }
    }

    override fun findByUserIdAndAlcoholId(userId: Long, alcoholId: Int): WishDto? {
        return transaction {
            Wishes
                    .join(Users, JoinType.LEFT, Wishes.userId, Users.id)
                    .selectAll()
                    .where {
                        (Wishes.userId eq userId) and (Wishes.alcoholId eq alcoholId)
                    }
                    .map { row ->
                        WishDto(
                                id = row[Wishes.id],
                                userId = row[Wishes.userId],
                                alcoholId = row[Wishes.alcoholId],
                                createdAt = row[Wishes.createdAt]
                        )
                    }.singleOrNull()
        }
    }

    override fun create(userId: Long, alcoholId: Int) {
        transaction {
            Wishes.insert {
                it[Wishes.userId] = userId
                it[Wishes.alcoholId] = alcoholId
                it[createdAt] = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            }
        }
    }

    override fun delete(userId: Long, alcoholId: Int) {
        transaction {
            Wishes.deleteWhere {
                (Wishes.userId eq userId) and (Wishes.alcoholId eq alcoholId)
            }
        }
    }
}