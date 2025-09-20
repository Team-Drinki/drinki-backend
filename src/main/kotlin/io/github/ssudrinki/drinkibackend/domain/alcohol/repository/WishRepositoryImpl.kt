package io.github.ssudrinki.drinkibackend.domain.alcohol.repository

import io.github.ssudrinki.drinkibackend.domain.alcohol.entity.WishDto
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import schema.Users
import schema.Wishes

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