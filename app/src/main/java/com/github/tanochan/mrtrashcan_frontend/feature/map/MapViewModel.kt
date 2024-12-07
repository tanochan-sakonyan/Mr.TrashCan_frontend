package com.github.tanochan.mrtrashcan_frontend.feature.map

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MapViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val TAG = "MapViewModel"
    private val _currentLocation = MutableStateFlow<LatLng?>(null)
    val currentLocation: StateFlow<LatLng?> = _currentLocation

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    @SuppressLint("MissingPermission")
    fun fetchCurrentLocation() {
        viewModelScope.launch {
            try {
                val location = fusedLocationClient.lastLocation.await()
                if (location != null) {
                    _currentLocation.value = LatLng(location.latitude, location.longitude)
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
}