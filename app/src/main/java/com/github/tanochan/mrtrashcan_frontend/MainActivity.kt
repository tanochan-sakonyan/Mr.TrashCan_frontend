package com.github.tanochan.mrtrashcan_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.tanochan.mrtrashcan_frontend.feature.map.mapScreenHost
import com.github.tanochan.mrtrashcan_frontend.feature.register.RegisterScreenHost
import com.github.tanochan.mrtrashcan_frontend.feature.screens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "Map",
            ){
                composable(screens.Map.route){
                    mapScreenHost(
                        navigateToRegister = {
                            navController.navigate(screens.Register.route)
                        }
                    )
                }
                composable(screens.Register.route){
                    RegisterScreenHost(
                        navigateToMap = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}