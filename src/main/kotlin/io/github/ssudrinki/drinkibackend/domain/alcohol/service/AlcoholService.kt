package io.github.ssudrinki.drinkibackend.domain.alcohol.service

import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.request.AlcoholSearchRequest
import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholDetailResponse
import io.github.ssudrinki.drinkibackend.domain.alcohol.dto.response.AlcoholListResponse

interface AlcoholService {

    /**
    fun searchAlcohols(alcoholSearchRequest: AlcoholSearchRequest): AlcoholListResponse
    fun getAlcoholDetail(alcoholId: Int): AlcoholDetailResponse
    fun addWish(alcoholId: Int)
    fun removeWish(alcoholId: Int)
//    fun getAlcoholById(id: Int): AlcoholDetailResponse?                                     //
//    fun createAlcohol(request: AlcoholCreateRequest): Int                                   // 새로운 술 등록
//    fun updateAlcohol(id: Int, name: String?, proof: Short?, content: String?): Boolean     // 술 수정
//    fun deleteAlcohol(id: Int): Boolean                                                     // 술 삭제
}