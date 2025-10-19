package io.github.teamdrinki.drinkibackend.domain.user.repository

import io.github.teamdrinki.drinkibackend.schema.UserEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.stereotype.Repository
import java.sql.Timestamp

@Repository
class UserRepositoryImpl(

) : UserRepository{
    override fun findById(userId: Long): UserEntity {
        return transaction {
            UserEntity.findById(userId)
                ?: throw NoSuchElementException("User with id $userId not found")
        }
    }

    override fun updateById(userId: Long, update: (UserEntity) -> Unit): UserEntity? {
        return UserEntity.findByIdAndUpdate(userId) { entity ->
            update(entity)
        }
    }

}