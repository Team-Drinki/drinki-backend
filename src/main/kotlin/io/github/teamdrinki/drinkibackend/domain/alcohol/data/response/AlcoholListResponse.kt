package io.github.teamdrinki.drinkibackend.domain.alcohol.data.response;

import io.github.teamdrinki.drinkibackend.common.util.PageUtil
import io.github.teamdrinki.drinkibackend.domain.alcohol.data.response.AlcoholListItem

data class AlcoholListResponse(
    val items: List<AlcoholListItem>,
    val pageUtil: PageUtil
)