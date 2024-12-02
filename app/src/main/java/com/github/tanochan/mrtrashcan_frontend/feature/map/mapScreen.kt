package com.github.tanochan.mrtrashcan_frontend.feature.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun mapScreenHost(
    navigateToRegister: () -> Unit
){
    mapScreen(
        onFabClick = navigateToRegister
    )
}

@Composable
fun mapScreen(
        onFabClick: () -> Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Register"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ){
            Text("This is mapScreen !")
        }
    }
}