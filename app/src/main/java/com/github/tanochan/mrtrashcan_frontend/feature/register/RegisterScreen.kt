package com.github.tanochan.mrtrashcan_frontend.feature.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tanochan.mrtrashcan_frontend.R

@Composable
fun RegisterScreenHost(
    navigateToMap: () -> Unit
) {
    RegisterScreen(
        onBack = navigateToMap
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onBack: () -> Unit
) {
    var landmark by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("")
                },
                navigationIcon = {
                    TextButton(
                        onClick = onBack, modifier = Modifier.padding(start = 28.dp)
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
                            .background(Color.Green)
                            .clickable { onBack() },
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
            Row (
                modifier = Modifier.align(Alignment.Start)
            ){
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
                IconButton(
                    onClick = { }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.burning),
                        contentDescription = "burning",
                    )
                }
                IconButton(
                    onClick = { }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.unburning),
                        contentDescription = "unburning",
                    )
                }
                IconButton(
                    onClick = { }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.can),
                        contentDescription = "can",
                    )
                }
                IconButton(
                    onClick = { }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bottle),
                        contentDescription = "bottle",
                    )
                }
                IconButton(
                    onClick = { }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pet_bottle),
                        contentDescription = "pet_bottle",
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row (
                modifier = Modifier.align(Alignment.Start)
            ){
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
                modifier = Modifier.size(width = 314.dp, height = 40.dp),
                value = landmark,
                onValueChange = { landmark = it },
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
            Row (
                modifier = Modifier.align(Alignment.Start)
            ){
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
            Row { }
            Spacer(modifier = Modifier.height(20.dp))
            Row (
                modifier = Modifier.align(Alignment.Start)
            ){
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
            // TODO camera

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
                value = landmark,
                onValueChange = { landmark = it },
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
    RegisterScreen(onBack = {})
}