package com.github.tanochan.mrtrashcan_frontend.feature.map

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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.tanochan.mrtrashcan_frontend.R
import com.github.tanochan.mrtrashcan_frontend.feature.RequestLocationPermission
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreenHost(
    navigateToRegister: () -> Unit
){
    val mapViewModel: MapViewModel = viewModel()
    MapScreen(
        onFabClick = navigateToRegister,
        mapViewModel = mapViewModel
    )
}

@Composable
fun MapScreen(
        onFabClick: () -> Unit,
        mapViewModel: MapViewModel
){
    val currentLocation by mapViewModel.currentLocation.collectAsState()

    Box(modifier = Modifier.fillMaxSize()){
        // カメラの初期位置を設定（例: 東京駅）
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
                // 必要に応じて追加
            )

            // 各ゴミ箱にマーカーを追加
            trashCanLocations.forEach { location ->
                Marker(
                    state = MarkerState(position = location),
                    title = "ゴミ箱",
                    snippet = "ここにゴミ箱があります"
                    // 必要に応じてカスタムアイコンを設定
                    // icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                )
            }

            currentLocation?.let { location ->
                Marker(
                    state = MarkerState(position = location),
                    title = "現在地",
                    snippet = "あなたの現在地",
                    // カスタムアイコンを使用する場合は以下を有効にして画像を指定
                    // icon = BitmapDescriptorFactory.fromResource(R.drawable.current_location_marker)
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE) // 青色のデフォルトマーカー
                )
            }
        }

        // 現在地のパーミッションを要求
        RequestLocationPermission (
            onPermissionGranted = {
                mapViewModel.fetchCurrentLocation()
            },
            onPermissionDenied = {
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
                tint = Color.Green
            )
        }

        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 84.dp, start = 24.dp),
            shape = CircleShape,
            containerColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.filter_alt),
                contentDescription = "Filter",
                tint = Color.Green
            )
        }

        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 84.dp, end = 24.dp),
            shape = CircleShape,
            containerColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.near_me),
                contentDescription = "Near Me",
                tint = Color.Green,
            )
        }

        Button(
            onClick = onFabClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .width(240.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "trashcan",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ごみ箱を報告",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    color = Color.White
                )
            }
        }
    }
}