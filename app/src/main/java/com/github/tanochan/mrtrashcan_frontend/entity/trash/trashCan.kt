package com.github.tanochan.mrtrashcan_frontend.entity.trash

import com.google.gson.annotations.SerializedName

data class TrashCan (
    @SerializedName("id")
    val id: Int,

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double,

    @SerializedName("image")
    val image: String,

    @SerializedName("trashType")
    val trashType: List<String>,
)