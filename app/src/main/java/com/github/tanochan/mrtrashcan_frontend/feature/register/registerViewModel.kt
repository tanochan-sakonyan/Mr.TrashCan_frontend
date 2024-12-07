package com.github.tanochan.mrtrashcan_frontend.feature.register

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
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
    private val apiService: ApiService,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var isBurningSelected: MutableState<Boolean> = mutableStateOf(false)
    var isUnBurningSelected: MutableState<Boolean> = mutableStateOf(false)
    var isCanSelected: MutableState<Boolean> = mutableStateOf(false)
    var isBottleSelected: MutableState<Boolean> = mutableStateOf(false)
    var isPetBottleSelected: MutableState<Boolean> = mutableStateOf(false)

    var landmark: MutableState<String> = mutableStateOf("")

    var selectedButton: MutableState<String> = mutableStateOf("")

    var photoUri: MutableState<String?> = mutableStateOf(savedStateHandle["photoUri"])
        private set

    var note: MutableState<String> = mutableStateOf("")

    fun toggleBurning() {
        isBurningSelected.value = !isBurningSelected.value
    }
    fun toggleUnBurning() {
        isUnBurningSelected.value = !isUnBurningSelected.value
    }
    fun toggleCan() {
        isCanSelected.value = !isCanSelected.value
    }
    fun toggleBottle() {
        isBottleSelected.value = !isBottleSelected.value
    }
    fun togglePetBottle() {
        isPetBottleSelected.value = !isPetBottleSelected.value
    }

    fun updateLandmark(newLandmark: String) {
        landmark.value = newLandmark
    }

    fun updateSelectedButton(newButton: String) {
        selectedButton.value = newButton
    }

    fun updatePhotoUri(uri: String) {
        Log.d("RegisterViewModel", "Updating photo URI to: $uri")
        photoUri.value = uri
        savedStateHandle["photoUri"] = uri
    }

    fun updateNote(newNote: String) {
        note.value = newNote
    }

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