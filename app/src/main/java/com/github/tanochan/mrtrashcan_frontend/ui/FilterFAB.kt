package com.github.tanochan.mrtrashcan_frontend.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.tanochan.mrtrashcan_frontend.R
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment

@Composable
fun FilterFab(
    onFilterSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Surface(
        modifier = if (expanded) {
            modifier
                .width(56.dp)
                .height(320.dp)
        } else {
            modifier.size(56.dp)
        },
        shape = if (expanded) RoundedCornerShape(16.dp) else CircleShape,
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        if (expanded) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FilterIcon(
                    iconResId = R.drawable.burning_on,
                    description = "燃えるゴミ",
                    onClick = { onFilterSelected("burning") }
                )
                FilterIcon(
                    iconResId = R.drawable.unburning_on,
                    description = "不燃ゴミ",
                    onClick = { onFilterSelected("unburning") }
                )
                FilterIcon(
                    iconResId = R.drawable.can_on,
                    description = "缶",
                    onClick = { onFilterSelected("can") }
                )
                FilterIcon(
                    iconResId = R.drawable.bottle_on,
                    description = "びん",
                    onClick = { onFilterSelected("bottle") }
                )
                FilterIcon(
                    iconResId = R.drawable.pet_bottle_on,
                    description = "ペットボトル",
                    onClick = { onFilterSelected("pet_bottle") }
                )
                FilterIcon(
                    iconResId = R.drawable.filter,
                    description = "フィルター",
                    onClick = { onFilterSelected("filter") }
                )
            }
        } else {
            // フィルターボタン表示
            FloatingActionButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.size(56.dp),
                shape = CircleShape,
                containerColor = Color.White
            ) {
                Image(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "Filter",
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}

@Composable
fun FilterIcon(
    iconResId: Int,
    description: String,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = iconResId),
        contentDescription = description,
        modifier = Modifier
            .size(48.dp)
            .clickable { onClick() }
    )
}

@Preview
@Composable
fun PreviewFilterFab() {
    FilterFab(onFilterSelected = { filter ->
        println("Filter selected: $filter")
    })
}