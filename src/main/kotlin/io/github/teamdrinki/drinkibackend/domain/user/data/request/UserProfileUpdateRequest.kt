package io.github.teamdrinki.drinkibackend.domain.user.data.request

import io.github.teamdrinki.drinkibackend.schema.Users
import org.hibernate.validator.constraints.URL

data class UserProfileUpdateRequest (
    val nickname        : String,
    val profileImageURL : String,
){}
