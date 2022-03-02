package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val headTextStyle = TextStyle(fontWeight = FontWeight.Bold)
val valueTextStyle = TextStyle(color = baseTextColor, fontSize = 12.sp)

@Composable
fun CertParseResultView(certificate: MutableState<MutableMap<String, Any>>) {

   certificate.value.forEach{
       line(it.key, it.value)
   }
}

@Composable
fun line(header: String, value: Any?) {
    val backgroundColor = rememberSaveable { mutableStateOf(Color.Transparent) }
    Row(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, bottom = 1.dp)
            .background(color = backgroundColor.value, shape = RectangleShape)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.LightGray)
            .height(52.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.2f)
                .background(baseBackgroundColor)
                .padding(start = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(header, style = headTextStyle)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
                .padding(start = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text("$value", style = valueTextStyle)
        }
    }
}