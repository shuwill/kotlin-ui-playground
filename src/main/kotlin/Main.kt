import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.FileDialog
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    application {
        Window(
            title = "Certificates Parser",
            onCloseRequest = ::exitApplication
        ) {
            val base64 = rememberSaveable { mutableStateOf("") }
            val readOnly = rememberSaveable { mutableStateOf(false) }
            MenuBar {
                Menu("File", mnemonic = 'F') {
                    Item("Open", onClick = {
                        val fileDialog = FileDialog(ComposeWindow())
                        fileDialog.isVisible = true
                        val readAllBytes = Files.readAllBytes(Paths.get(fileDialog.directory, fileDialog.file))
                        base64.value = Base64.getEncoder().encodeToString(readAllBytes)
                        readOnly.value = true
                    }, shortcut = KeyShortcut(Key.O, ctrl = true))
                }
            }
            app(base64, readOnly)
        }
    }
}