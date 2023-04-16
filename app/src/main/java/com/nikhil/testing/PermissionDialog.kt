package com.nikhil.testing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermissionDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Divider()
                Text(
                    text = if (isPermissionDeclined) {
                        "Grant Permission"
                    } else {
                        "Ok"
                    },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isPermissionDeclined) {
                                onGoToAppSettingClick()
                            } else {
                                onOkClick()
                            }
                        }
                        .padding(16.dp)
                )
            }
        },
        title = {
            Text(text = "Permission Required")
        },
        text = {
            Text(
                text = permissionTextProvider.getDescription(
                    isPermissionDeclined = isPermissionDeclined
                )
            )
        },
        modifier = modifier
    )
}

interface PermissionTextProvider{
    fun getDescription(isPermissionDeclined: Boolean): String
}

class CameraPermissionTextProvider: PermissionTextProvider{
    override fun getDescription(isPermissionDeclined: Boolean): String {
        return if (isPermissionDeclined){
            "It seems you permanently declined camera permission." +
                    "You can got to App settigns to grant it."
        }else{
            "This app needs a Camera permission."
        }
    }

}

class RecordAudioPermissionTextProvider: PermissionTextProvider{
    override fun getDescription(isPermissionDeclined: Boolean): String {
        return if (isPermissionDeclined){
            "It seems you permanently declined microphone permission." +
                    "You can got to App settigns to grant it."
        }else{
            "This app needs a microphone permission."
        }
    }

}

class PhoneCallPermissionTextProvider: PermissionTextProvider{
    override fun getDescription(isPermissionDeclined: Boolean): String {
        return if (isPermissionDeclined){
            "It seems you permanently declined call permission." +
                    "You can got to App settigns to grant it."
        }else{
            "This app needs a call permission."
        }
    }

}
