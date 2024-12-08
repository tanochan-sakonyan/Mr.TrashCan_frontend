import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tanochan.mrtrashcan_frontend.R

@Composable
fun BottomSheetContent() {
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(height = 56.dp, width = 56.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                        )
                ) {
                    // TODO ピンの場所の画像を表示
                    Image(
                        painter = painterResource(id = R.drawable.trash_can),
                        contentScale = ContentScale.Crop,
                        contentDescription = "ゴミ箱の画像",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(18.dp))
                Column {
                    Text(
                        text = "原宿駅西口前",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .size(
                                width = 46.dp,
                                height = 25.dp,
                            )
                            .clip(shape = RoundedCornerShape(6.dp))
                            .background(
                                Color(0xFF50BCA3)
                            )
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "改札内",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.burning_on),
                        contentDescription = "burning",
                    )
                    Text(
                        text = "燃えるゴミ",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color(0xFF656565)
                        )
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.unburning_off),
                        contentDescription = "unburning",
                    )
                    Text(
                        text = "燃えないゴミ",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color(0xFF656565)
                        )
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.can_on),
                        contentDescription = "can",
                    )

                    Text(
                        text = "カン",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color(0xFF656565)
                        )
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bottle_on),
                        contentDescription = "bottle",
                    )
                    Text(
                        text = "ビン",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color(0xFF656565)
                        )
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pet_bottle_off),
                        contentDescription = "pet_bottle",
                    )
                    Text(
                        text = "ペットボトル",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color(0xFF656565)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "備考",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8E8E8E)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "自動販売機の隣にある。",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(27.dp))

            TextButton(
                onClick = {
                    // TODO 修正画面に遷移
                },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "ゴミ箱情報が間違ってる？ゴミ箱情報を修正する",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF50BCA3),
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomSheetContent() {
    BottomSheetContent()
}
