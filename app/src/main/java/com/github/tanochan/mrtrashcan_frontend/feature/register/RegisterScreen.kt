package com.github.tanochan.mrtrashcan_frontend.feature.register

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.github.tanochan.mrtrashcan_frontend.R
import com.github.tanochan.mrtrashcan_frontend.ui.component.CustomElevatedButton

@Composable
fun RegisterScreenHost(
    navigateToMap: () -> Unit,
    navigateToCamera: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    LaunchedEffect(Unit) {
        viewModel.loadCurrentLocation()
    }

    RegisterScreen(
        onBack = {
            viewModel.clearCurrentLocation();
            viewModel.logCurrentLocation();
            navigateToMap()
                 },
        onCameraClick = { navigateToCamera() },
        viewModel = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onCameraClick: () -> Unit,
    viewModel: RegisterViewModel,
) {
    val isBurningSelected = viewModel.isBurningSelected.value
    val isUnBurningSelected = viewModel.isUnBurningSelected.value
    val isCanSelected = viewModel.isCanSelected.value
    val isBottleSelected = viewModel.isBottleSelected.value
    val isPetBottleSelected = viewModel.isPetBottleSelected.value

    val landmark = viewModel.landmark.value

    val selectedButton = viewModel.selectedButton.value

    val photoUri = viewModel.photoUri.value

    val note = viewModel.note.value

    val isPostable = (isBurningSelected || isUnBurningSelected || isCanSelected ||
            isBottleSelected || isPetBottleSelected) &&
            landmark.isNotEmpty() &&
            selectedButton.isNotEmpty() &&
            photoUri != null

    val currentLocation by viewModel.currentLocation.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("")
                },
                navigationIcon = {
                    TextButton(
                        onClick = {
                            // TODO 内容リセット処理
                            onBack()
                        },
                        modifier = Modifier.padding(start = 28.dp)
                    ) {
                        Text(
                            text = "キャンセル", style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 28.dp)
                            .size(
                                width = 84.dp,
                                height = 36.dp,
                            )
                            .clip(shape = RoundedCornerShape(35.dp))
                            .background(
                                brush = Brush.linearGradient(
                                    colors = if(isPostable) {
                                        listOf(
                                            Color(0xFF50BCA3),
                                            Color(0xFF4FBBA9)
                                        )
                                    } else
                                    listOf(
                                        Color(0xFF75FF8C).copy(alpha = 0.3f),
                                        Color(0xFF4FBBA9).copy(alpha = 0.4f)
                                    )
                                )
                            )
                            .clickable (enabled = isPostable) {
                                // TODO ゴミ箱登録処理
                                // TODO 内容リセット処理
                                onBack()
                            },
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "投稿",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }
            )
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 28.dp),
                    text = "ゴミ種別",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = "*",
                    color = Color(0xFFF57474),
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,

                ) {
                IconButton(onClick = { viewModel.toggleBurning() }) {
                    Image(
                        painter = painterResource(id = if (isBurningSelected) R.drawable.ic_burning_on else R.drawable.ic_burning_off),
                        contentDescription = "burning",
                    )
                }
                IconButton(onClick = { viewModel.toggleUnBurning() }) {
                    Image(
                        painter = painterResource(id = if (isUnBurningSelected) R.drawable.ic_unburning_on else R.drawable.ic_unburning_off),
                        contentDescription = "unburning",
                    )
                }
                IconButton(onClick = { viewModel.toggleCan() }) {
                    Image(
                        painter = painterResource(id = if (isCanSelected) R.drawable.ic_can_on else R.drawable.ic_can_off),
                        contentDescription = "can",
                    )
                }
                IconButton(onClick = { viewModel.toggleBottle() }) {
                    Image(
                        painter = painterResource(id = if (isBottleSelected) R.drawable.ic_bottle_on else R.drawable.ic_bottle_off),
                        contentDescription = "bottle",
                    )
                }
                IconButton(onClick = { viewModel.togglePetBottle() }) {
                    Image(
                        painter = painterResource(id = if (isPetBottleSelected) R.drawable.ic_pet_bottle_on else R.drawable.ic_pet_bottle_off),
                        contentDescription = "pet_bottle",
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 28.dp),
                    text = "一番近いランドマーク",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = "*",
                    color = Color(0xFFF57474),
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier.size(width = 314.dp, height = 60.dp),
                value = landmark,
                onValueChange = { viewModel.updateLandmark(it) },
                label = {
                    Text(
                        "例）XX駅西口、ローソン、○○公園", style = TextStyle(
                            fontSize = 12.sp,
                        )
                    )
                },
                shape = RoundedCornerShape(2.dp),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                ),
                colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 28.dp),
                    text = "場所",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = "*",
                    color = Color(0xFFF57474),
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 28.dp),
            ) {
                CustomElevatedButton(
                    title = "改札内",
                    isSelected = selectedButton == "insideGate",
                    onClick = { viewModel.updateSelectedButton("insideGate") }
                )
                Spacer(modifier = Modifier.width(16.dp))
                CustomElevatedButton(
                    title = "建物内",
                    isSelected = selectedButton == "insideBuilding",
                    onClick = { viewModel.updateSelectedButton("insideBuilding") }
                )
                Spacer(modifier = Modifier.width(16.dp))
                CustomElevatedButton(
                    title = "屋外",
                    isSelected = selectedButton == "outside",
                    onClick = { viewModel.updateSelectedButton("outside") }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 28.dp),
                    text = "写真",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = "*",
                    color = Color(0xFFF57474),
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(height = 132.dp, width = 132.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                    )
            ) {
                if (photoUri != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clickable { onCameraClick() }
                            .fillMaxSize()
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(photoUri),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Captured photo",
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clickable { onCameraClick() }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = "Take photo",
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .padding(start = 28.dp)
                    .align(Alignment.Start),
                text = "備考",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.size(width = 314.dp, height = 100.dp),
                value = note,
                onValueChange = { viewModel.updateNote(it) },
                label = {
                    Text(
                        "例）自販機横、トイレ横", style = TextStyle(
                            fontSize = 12.sp,
                            color = Color(0xFFB9B9B9)
                        )
                    )
                },
                shape = RoundedCornerShape(2.dp),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                ),
                colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color(0xFFB9B9B9),
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(onBack = {}, onCameraClick = {}, viewModel = viewModel())
}