package com.github.tanochan.mrtrashcan_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.tanochan.mrtrashcan_frontend.feature.map.mapScreen
import com.github.tanochan.mrtrashcan_frontend.feature.map.mapScreenHost
import com.github.tanochan.mrtrashcan_frontend.feature.register.registerScreenHost
import com.github.tanochan.mrtrashcan_frontend.feature.screens
import com.github.tanochan.mrtrashcan_frontend.ui.theme.MrTrashCan_frontendTheme

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
                    registerScreenHost(
                        navigateToMap = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}