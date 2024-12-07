package com.github.tanochan.mrtrashcan_frontend.feature.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

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
        bottomBar = {

                // ボタンを中央に配置するために Row を使用
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onFabClick,
                        modifier = Modifier
                            .padding(16.dp) // ボタンの周囲にパディングを追加
                            .fillMaxWidth(0.9f) // ボタンの幅を画面幅の90%に設定
                    ) {
                        Text(text = "ゴミ箱を登録")
                    }
                }

        },
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
            //TODO: ここにバックから取得した現在地を入力
            // カメラの初期位置を設定（例: 東京駅）
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(
                    LatLng(35.681236, 139.767125), // 東京駅の座標
                    15f // ズームレベル
                )
            }

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
            }
        }
    }
}

@Preview
@Composable
fun mapScreenPreview(){
    mapScreen(onFabClick = {})
}