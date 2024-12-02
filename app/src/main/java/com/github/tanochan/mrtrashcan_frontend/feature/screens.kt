package com.github.tanochan.mrtrashcan_frontend.feature

sealed class screens(val route : String) {
    object Map: screens("map")
    object Register: screens("register")
}