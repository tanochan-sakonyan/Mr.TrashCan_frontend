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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tanochan.mrtrashcan_frontend.R

@Composable
fun BottomSheetContent() {
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
            ){

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
            Image(
                painter = painterResource(id = R.drawable.burning_on),
                contentDescription = "burning",
            )
            Image(
                painter = painterResource(id = R.drawable.unburning_off),
                contentDescription = "unburning",
            )
            Image(
                painter = painterResource(id = R.drawable.can_on),
                contentDescription = "can",
            )
            Image(
                painter = painterResource(id = R.drawable.bottle_on),
                contentDescription = "bottle",
            )
            Image(
                painter = painterResource(id = R.drawable.pet_bottle_off),
                contentDescription = "pet_bottle",
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "備考",
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "自動販売機の隣にある。",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                // TODO 修正画面に遷移
            }
        ) {
            Text(text = "ゴミ箱情報が間違ってる？ゴミ箱情報を修正する")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomSheetContent() {
    BottomSheetContent()
}
