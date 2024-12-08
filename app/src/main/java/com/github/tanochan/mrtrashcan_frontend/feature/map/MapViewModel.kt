package com.github.tanochan.mrtrashcan_frontend.feature.map

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tanochan.mrtrashcan_frontend.core.ApiService
import com.github.tanochan.mrtrashcan_frontend.core.PreferencesManager
import com.github.tanochan.mrtrashcan_frontend.core.model.TrashCan
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient,
    private val apiService: ApiService,
    private val preferencesManager: PreferencesManager
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

    private val TAG = "MapViewModel"
    private val _currentLocation = MutableStateFlow<LatLng?>(null)
    val currentLocation: StateFlow<LatLng?> = _currentLocation

    fun saveCurrentLocation(location: LatLng) {
        preferencesManager.saveLocation(location)
    }

    @SuppressLint("MissingPermission")
    fun fetchCurrentLocation() {
        viewModelScope.launch {
            try {
                val location = fusedLocationClient.lastLocation.await()
                if (location != null) {
                    _currentLocation.value = LatLng(location.latitude, location.longitude)
                    saveCurrentLocation(currentLocation.value!!)
                    Log.d(TAG, "Location fetched: ${location.latitude}, ${location.longitude}")
                } else {
                    Log.d(TAG, "Location is null")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e(TAG, "Error fetching location", e)
            }
        }
    }

    fun setDefaultLocation(defaultLocation: LatLng) {
        _currentLocation.value = defaultLocation
    }

}