package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun CertBase64InputView(textInput: MutableState<String>, readOnly: MutableState<Boolean>) {

    val placeholder = """-----BEGIN CERTIFICATE REQUEST-----
MIHqMIGVAgEAMDAxFjAUBgNVBAMMDWV4YW1wbGUubG9jYWwxFjAUBgNVBAoMDURl
bW9uc3RyYXRpb24wXDANBgkqhkiG9w0BAQEFAANLADBIAkEAqu7qhOa63jTfT3Kd
Axp53ep7HHiJ9F6n6SIqBOeIqIStHK2wKT6PCk8qjRyHIz0nBiNT8gfYumzcAa+V
8nX11QIDAQABoAAwDQYJKoZIhvcNAQELBQADQQCVwaST6W+IYTR5OPPSTUif+kjL
3q0PgPEMg8pOLCW099+IU53PjsMxveFl+PzmNOq+VoXA/BEy9sv4EEaDkvtY
-----END CERTIFICATE REQUEST----- """

    val placeholderTextStyle = TextStyle(
        color = secondaryBackgroundColor,
        fontSize = 10.sp,
        lineHeight = 10.sp
    )

    Row(horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            value = textInput.value,
            placeholder = {
                Text(
                    placeholder,
                    style = placeholderTextStyle
                )
            },
            textStyle = TextStyle(fontSize = 12.sp),
            readOnly = readOnly.value,
            modifier = Modifier.padding(20.dp).fillMaxWidth().weight(2f).background(Color(247, 248, 251)),
            shape = RectangleShape,
            onValueChange = {
                textInput.value = it
            }
        )
    }
}