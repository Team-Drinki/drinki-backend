package io.github.teamdrinki.drinkibackend.domain.alcohol.repository

import io.github.teamdrinki.drinkibackend.common.dto.PagedListResult

import io.github.teamdrinki.drinkibackend.schema.Alcohols
import io.github.teamdrinki.drinkibackend.schema.Wishes
import io.github.teamdrinki.drinkibackend.schema.entity.AlcoholEntity
import io.github.teamdrinki.drinkibackend.schema.entity.WishEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class WishRepositoryImpl : WishRepository {

    override fun findByUserId(page: Int, size: Int, sort: String,
                              userId: Long): PagedListResult<AlcoholEntity> {
        val offset = ((page-1)*size)

        return transaction {
            val query = AlcoholEntity
                .wrapRows(
                    Wishes.innerJoin(Alcohols)
                        .select(Alcohols.columns)
                        .where { Wishes.userId eq userId }
                )

            val totalCnt = query.count()
            val entities = query
                .orderBy(Wishes.createdAt to SortOrder.DESC)
                .drop(offset)
                .take(size)
                .toList()

            PagedListResult(entities, totalCnt)
        }
    }

    override fun findByUserIdAndAlcoholId(userId: Long, alcoholId: Int): WishEntity? {
        return transaction {
            WishEntity
                .find { Wishes.userId eq userId }
                .singleOrNull()
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