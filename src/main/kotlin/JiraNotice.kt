import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {

    val isVisible = rememberSaveable { mutableStateOf(true) }

    Window(
        onCloseRequest = { isVisible.value = false },
        visible = isVisible.value,
        title = "JiraNotice"
    ) {
    }
}