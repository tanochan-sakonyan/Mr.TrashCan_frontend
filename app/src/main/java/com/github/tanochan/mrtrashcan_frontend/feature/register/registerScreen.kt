package com.github.tanochan.mrtrashcan_frontend.feature.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun registerScreenHost(
    navigateToMap: () -> Unit
){
    registerScreen(
        onBack = navigateToMap
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun registerScreen(
    onBack: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "ごみ箱登録")},
                navigationIcon = {IconButton(
                    onClick = onBack
                ) {
                    Icon(Icons.Default.ArrowBack, "go back to map screen")
                }}
            )
                 },

    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Text("This is registerScreen")
        }
    }
}