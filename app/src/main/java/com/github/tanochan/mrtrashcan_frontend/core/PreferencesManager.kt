package com.github.tanochan.mrtrashcan_frontend.core

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.android.gms.maps.model.LatLng

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    fun saveLocation(location: LatLng) {
        prefs.edit().apply {
            putFloat("latitude", location.latitude.toFloat())
            putFloat("longitude", location.longitude.toFloat())
            apply()
        }
    }

    fun getLocation(): LatLng? {
        val latitude = prefs.getFloat("latitude", Float.MIN_VALUE)
        val longitude = prefs.getFloat("longitude", Float.MIN_VALUE)
        return if (latitude != Float.MIN_VALUE && longitude != Float.MIN_VALUE) {
            LatLng(latitude.toDouble(), longitude.toDouble())
        } else {
            null
        }
    }

    fun clearLocation() {
        prefs.edit().apply {
            remove("latitude")
            remove("longitude")
            apply()
        }
    }

    //デバッグ用
    fun logCurrentLocation() {
        val latitude = prefs.getFloat("latitude", Float.MIN_VALUE)
        val longitude = prefs.getFloat("longitude", Float.MIN_VALUE)
        if (latitude != Float.MIN_VALUE && longitude != Float.MIN_VALUE) {
            Log.d("PreferencesManager", "Saved location: latitude=$latitude, longitude=$longitude")
        } else {
            Log.d("PreferencesManager", "No location saved in SharedPreferences.")
        }
    }
}