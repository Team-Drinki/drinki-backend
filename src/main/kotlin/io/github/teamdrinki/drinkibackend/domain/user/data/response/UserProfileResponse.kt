package io.github.teamdrinki.drinkibackend.domain.user.data.response

import io.github.teamdrinki.drinkibackend.schema.Users
import kotlinx.datetime.LocalDateTime
import java.sql.Timestamp

data class UserProfileResponse(
    val socialType: String,
    val nickname:   String,
    val createdAt:  LocalDateTime,
) {}
