package org.cartify.ecommerce.feature_login.presentation.ui.otp

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import org.cartify.ecommerce.presentation.ui.theme.CartifyTheme
import org.cartify.ecommerce.presentation.ui.theme.PurpleGrey30
import org.cartify.ecommerce.presentation.ui.theme.White0


@Composable
fun OtpInputField(
    number: Int?,
    focusRequester: FocusRequester,
    onFocusChanged: (Boolean) -> Unit,
    onKeyboardBack: () -> Unit,
    onNumberChanged: (Int?) -> Unit,
    modifier: Modifier
) {
    // Properly manage the TextFieldValue with text and selection
    var text by remember(number) {
        mutableStateOf(
            TextFieldValue(
                text = number?.toString().orEmpty(),
                selection = TextRange(number?.toString()?.length ?: 0)
            )
        )
    }

    var isFocused by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .border(
                width =if(isFocused) 2.dp else 1.dp,
                color =if(isFocused) MaterialTheme.colorScheme.secondary else PurpleGrey30,
            ),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = text,

            onValueChange = { newText ->
                val newNumber = newText.text

                // Update text if it's a valid number and limit the length to 1
                if (newNumber.length <= 1 && newNumber.isDigitsOnly()) {
                    onNumberChanged(newNumber.toIntOrNull()) // Update the number externally
                } else {
                    Log.e("newNumber", newNumber)
                }
            },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.tertiary),
            singleLine = true,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.tertiary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocused = it.isFocused
                    onFocusChanged(it.isFocused)
                }
                .onKeyEvent { event ->
                    val isDeleteButtonPressed =
                        event.nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_DEL

                    if (number == null && isDeleteButtonPressed) {
                        onKeyboardBack()
                    }
                    false
                },
            decorationBox = { innerBox ->
                if (!isFocused && number == null) {
                    Text(
                        text = "-",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.fillMaxSize().wrapContentSize()
                    )
                }else{
                    innerBox()
                }
            }
        )
    }
}


@Preview
@Composable
fun OtpInputFieldPreview(
) {
    CartifyTheme {
        OtpInputField(
            number = null,
            focusRequester = remember {
                FocusRequester()
            },
            onFocusChanged = {},
            onKeyboardBack = {},
            onNumberChanged = {},
            modifier = Modifier.size(100.dp)
        )
    }
}