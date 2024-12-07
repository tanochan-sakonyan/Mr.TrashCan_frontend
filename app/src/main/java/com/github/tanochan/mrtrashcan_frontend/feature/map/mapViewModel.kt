package com.github.tanochan.mrtrashcan_frontend.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tanochan.mrtrashcan_frontend.core.ApiService
import com.github.tanochan.mrtrashcan_frontend.core.model.TrashCan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {

    fun getTrashCanList(latitude: Double, longitude: Double, onResult: (List<TrashCan>?) -> Unit) {
        viewModelScope.launch {
            try {
                val trashCanList = apiService.getTrashCanList(latitude, longitude)
                onResult(trashCanList)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(null)
            }
        }
    }

}