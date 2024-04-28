package com.example.mynotes.component

import android.content.pm.PackageManager.ComponentEnabledSetting
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text : String,
    label: String,
    onTextChange: (String) -> Unit,
    maxLine :Int = 1,
    onImeAction: () ->Unit = {}
)
{
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = {Text(label)},
        maxLines = maxLine,
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }
        )
    )
}

@Composable
fun NoteButton(
    modifier:Modifier = Modifier,
    text:String,
    onClick:()->Unit,
    enabled:Boolean = false,
    colors : ButtonColors
)
{
    Button(onClick = { onClick.invoke() },
        enabled = enabled,
        modifier = modifier,
        shape = CircleShape,
        colors = colors
        ) {
        Text(
            text,
            color = Color.Black
        )
    }
}