package com.github.tanochan.mrtrashcan_frontend.feature.map

import BottomSheetContent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.tanochan.mrtrashcan_frontend.R
import com.github.tanochan.mrtrashcan_frontend.feature.RequestLocationPermission
import com.github.tanochan.mrtrashcan_frontend.ui.ClickableBox
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreenHost(
    navigateToRegister: () -> Unit,
    mapViewModel: MapViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
){
    MapScreen(
        onFabClick = navigateToRegister,
        mapViewModel = mapViewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
        onFabClick: () -> Unit,
        mapViewModel: MapViewModel
){
    val currentLocation by mapViewModel.currentLocation.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )


    Box(modifier = Modifier.fillMaxSize()){
        // カメラの初期位置を(0, 0)に設定
        val cameraPositionState = rememberCameraPositionState()

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            //TODO: ここにバックから取得したゴミ箱リストを入力
            // ゴミ箱の座標リスト（ここに実際のデータを追加）
            val trashCanLocations = listOf(
                LatLng(35.681236, 139.767125), // 東京駅
                LatLng(35.6895, 139.6917)      // 新宿駅
            )

            // 各ゴミ箱にマーカー（ピン）を立てる
            trashCanLocations.forEach { location ->
                Marker(
                    state = MarkerState(position = location),
                    title = "ゴミ箱",
                    snippet = "ここにゴミ箱があります",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
                    onClick = {
                        showBottomSheet = true
                        false
                    }
                )
            }

            //自身の現在地にマーカーをつける
            currentLocation?.let { location ->
                Marker(
                    state = MarkerState(position = location),
                    title = "現在地",
                    snippet = "あなたの現在地",
                    icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_current_location)
                )
            }
        }

        // 現在地取得の権限を要求
        RequestLocationPermission (
            onPermissionGranted = {
                mapViewModel.fetchCurrentLocation()
            },
            //拒否されたら東京駅をデフォルトの現在地に設定
            onPermissionDenied = {
                mapViewModel.setDefaultLocation(LatLng(35.681236, 139.767125)) // 東京駅の座標
            }
        )

        // 現在地が更新されたらカメラを移動
        currentLocation?.let { location ->
            LaunchedEffect(location) {
                cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(location, 15f))
            }
        }

        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 56.dp, start = 24.dp),
            shape = CircleShape,
            containerColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
                modifier = Modifier.size(36.dp),
                tint = Color.Green
            )
        }

        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 56.dp, end = 24.dp),
            shape = CircleShape,
            containerColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Setting",
                modifier = Modifier.size(36.dp),
                tint = Color.Green
            )
        }

        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 120.dp, start = 24.dp),
            shape = CircleShape,
            containerColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.filter_alt),
                contentDescription = "Filter",
                modifier = Modifier.size(36.dp),
                tint = Color.Green
            )
        }

        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 120.dp, end = 24.dp)
                .size(72.dp),
            shape = CircleShape,
            containerColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.near_me),
                contentDescription = "Near Me",
                modifier = Modifier.size(42.dp),
                tint = Color.Green,
            )
        }

        ClickableBox(
            onClick = {
                currentLocation?.let { location ->
                    mapViewModel.saveCurrentLocation(location)
                    onFabClick()
                }
            },
            text = "ごみ箱を報告",
            iconPainter = painterResource(id = R.drawable.delete),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            BottomSheetContent()
        }
    }

}