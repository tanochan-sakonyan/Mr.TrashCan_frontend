package com.github.tanochan.mrtrashcan_frontend.core

import com.github.tanochan.mrtrashcan_frontend.core.model.TrashCan
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @GET("example.com/trashcan")
    suspend fun getTrashCanList(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): List<TrashCan>

    @Multipart
    @POST("example.com/trashcan")
    suspend fun registerTrashCan(
        @Part("latitude") latitude: Double,
        @Part("longitude") longitude: Double,
        @Part("trashType") trashType: List<String>,
        @Part image: MultipartBody.Part
    ): TrashCan

}
