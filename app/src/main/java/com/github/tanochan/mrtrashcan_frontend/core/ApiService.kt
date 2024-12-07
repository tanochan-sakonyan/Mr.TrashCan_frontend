package com.github.tanochan.mrtrashcan_frontend.core

import com.github.tanochan.mrtrashcan_frontend.core.model.TrashCan
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("example.com/trashcan")
    suspend fun getTrashCanList(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): List<TrashCan>

    @POST("example.com/trashcan")
    suspend fun registerTrashCan(
        @Body trashCan: TrashCan
    ): TrashCan
}
