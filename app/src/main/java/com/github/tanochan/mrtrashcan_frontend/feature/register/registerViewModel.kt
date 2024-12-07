package com.github.tanochan.mrtrashcan_frontend.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tanochan.mrtrashcan_frontend.core.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {
    fun registerTrashCan(latitude: Double, longitude: Double, trashType: List<String>, imageFile: File) {
        viewModelScope.launch {
            try {
                val imagePart = prepareFilePart("image", imageFile)

                val response = apiService.registerTrashCan(
                    latitude = latitude,
                    longitude = longitude,
                    trashType = trashType,
                    image = imagePart
                )

                println("Trash can registered successfully: $response")

            } catch (e: Exception) {
                println("Error registering trash can: ${e.message}")
            }
        }
    }

    private fun prepareFilePart(partName: String, file: File): MultipartBody.Part {
        val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }

}