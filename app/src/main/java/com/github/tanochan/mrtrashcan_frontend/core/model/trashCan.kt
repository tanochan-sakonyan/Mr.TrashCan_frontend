package com.github.tanochan.mrtrashcan_frontend.core.model

import com.google.gson.annotations.SerializedName
import java.io.File

data class TrashCan (
    @SerializedName("id")
    val id: Int,

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double,

    @SerializedName("image")
    val image: File,

    @SerializedName("trashType")
    val trashType: List<String>,
)