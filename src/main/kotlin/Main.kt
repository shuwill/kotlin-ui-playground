import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.application
import java.util.*

@Composable
@Preview
fun app() {

    val windowsColors = darkColors(
        surface = Color.DarkGray,
        onSurface = Color.DarkGray,
        background = Color.DarkGray,
        onBackground = Color.DarkGray
    )

    val textInput = rememberSaveable { mutableStateOf("Enter Your Value") }
    val textOutput = rememberSaveable { mutableStateOf("") }

    DesktopMaterialTheme(colors = windowsColors) {
        Surface(Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
                TextField(
                    value = textInput.value,
                    modifier = Modifier.fillMaxWidth().padding(start = 40.dp, end = 40.dp, top = 10.dp),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    shape = RectangleShape,
                    onValueChange = {
                        textInput.value = it
                    })

                Row(Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier.padding(start = 40.dp, top = 10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(73, 156, 84), contentColor = Color.White),
                        shape = RectangleShape,
                        onClick = {
                        }) {
                        Text(text = "Base64解码", fontSize = 12.sp)
                    }
                    Spacer(Modifier.weight(1f))
                    Button(
                        modifier = Modifier.padding(end = 40.dp, top = 10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(73, 156, 84), contentColor = Color.White),
                        shape = RectangleShape,
                        onClick = {
                            val encodeToString = Base64.getEncoder().encodeToString(textInput.value.toByteArray())
                            textOutput.value = encodeToString
                            println(textOutput.value)
                        }) {
                        Text(text = "Base64编码", fontSize = 12.sp)
                    }
                }

                TextField(
                    value = textOutput.value,
                    modifier = Modifier.fillMaxWidth().padding(start = 40.dp, end = 40.dp, top = 10.dp),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    shape = RectangleShape,
                    onValueChange = {

                    })
            }
        }
    }
}

@Composable
private fun WindowScope.AppWindowTitleBar() = WindowDraggableArea {
    Box(Modifier.fillMaxWidth().height(48.dp).background(Color.DarkGray))
}

fun main() = application {
    Window(
        title = "Koltin UI Playground",
        icon = BitmapPainter(useResource("icon.png", ::loadImageBitmap)),
        onCloseRequest = ::exitApplication,
        undecorated = false
    ) {
        app()
    }
}
