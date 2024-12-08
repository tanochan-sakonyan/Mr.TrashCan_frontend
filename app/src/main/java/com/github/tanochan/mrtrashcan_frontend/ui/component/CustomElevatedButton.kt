package com.github.tanochan.mrtrashcan_frontend.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomElevatedButton(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(
                width = 62.dp,
                height = 36.dp,
            )
            .clip(shape = RoundedCornerShape(6.dp))
            .background(
                if (isSelected) Color(0xFF50BCA3) else
                    Color.White
            )
            .border(
                width = 1.5.dp,
                color = if (isSelected) Color.White else Color(0xFF50BCA3),
                shape = RoundedCornerShape(6.dp)
            )
            .clickable {
                onClick()
            },
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color =
                if (isSelected) Color.White else
                    Color(0xFF50BCA3)
            )
        )
    }
}

@Preview
@Composable
fun CustomElevatedButtonPreviewOff() {
    CustomElevatedButton(
        title = "投稿",
        isSelected = false,
        onClick = {}
        )
}

@Preview
@Composable
fun CustomElevatedButtonPreviewOn() {
    CustomElevatedButton(
        title = "投稿",
        isSelected = true,
        onClick = {}
    )
}