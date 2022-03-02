import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.rememberNotification
import androidx.compose.ui.window.rememberTrayState
import cert.Certificate
import cert.parserByBase64
import cert.parserToMapByBase64
import ui.CertBase64InputView
import ui.CertParseResultView
import ui.baseBackgroundColor
import ui.baseTextColor

@Composable
fun app(base64: MutableState<String>, readOnly: MutableState<Boolean>) {

    Surface(modifier = Modifier.fillMaxSize()) {
        MaterialTheme {

            val stateVertical = rememberScrollState(0)
            val certificateState = rememberSaveable { mutableStateOf<MutableMap<String, Any>>(mutableMapOf()) }

            Column(Modifier.fillMaxSize().verticalScroll(stateVertical)) {
                val padding = 20.dp
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = padding),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Certificate Decoder",
                        style = MaterialTheme.typography.h2,
                        fontSize = 45.sp,
                        textAlign = TextAlign.Center,
                        color = baseTextColor
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(padding),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Submit your base64 encoded certificate in the field below. We will attempt to decode and analyze it to detect issues with it if any.",
                        textAlign = TextAlign.Center,
                        color = baseTextColor
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .background(baseBackgroundColor),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CertBase64InputView(base64, readOnly)
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        Button(modifier = Modifier.padding(end = 5.dp), onClick = {
                            val certificate = try {
                                parserToMapByBase64(base64.value)
                            } catch (e: Exception) {
                                null
                            }
                            if (certificate != null) {
                                certificateState.value = certificate
                            }
                        }) {
                            Text("DECODE")
                        }
                        Button(modifier = Modifier.padding(start = 5.dp), onClick = {
                            base64.value = ""
                            readOnly.value = false
                            certificateState.value = mutableMapOf()
                        }) {
                            Text("CLEAN")
                        }
                    }
                }

                CertParseResultView(certificateState)
                Spacer(modifier = Modifier.padding(bottom = padding))
            }
        }
    }
}
