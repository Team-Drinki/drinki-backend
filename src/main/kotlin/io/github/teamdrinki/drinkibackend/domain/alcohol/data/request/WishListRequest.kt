package io.github.teamdrinki.drinkibackend.domain.alcohol.data.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern

data class WishListRequest(
    @field:Min(value = 1, message = "페이지는 1 이상이어야 합니다")
    @field:Max(value = 1000, message = "페이지는 1000 이하여야 합니다")
    val page: Int = 1,

    @field:Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다")
    @field:Max(value = 100, message = "페이지 크기는 100 이하여야 합니다")
    val size: Int = 10,

    @field:Pattern(
        regexp = "^(CreatedAt|View|TastingNote|Like|Rating|PriceDesc|PriceAsc)$",
        message = "정렬 방식은 CreatedAt, View, TastingNote, Like, Rating, PriceDesc, PriceAsc 중 하나여야 합니다"
    )
    val sort: String = "CreatedAt",
    ) {}