package org.cartify.ecommerce.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.cartify.ecommerce.R
import org.cartify.ecommerce.presentation.ui.theme.SpaceMedium
import org.cartify.ecommerce.presentation.ui.theme.SpaceSmall


@Composable
fun StandardTextField(
    number: String?,
    isError: Boolean=false,
    focusRequester: FocusRequester,
    onNumberChanged: (String?) -> Unit,
) {

    var text by remember {
        mutableStateOf(
            TextFieldValue(
                text = number.orEmpty(),
                selection = if (number.isNullOrEmpty()) {
                    TextRange(0)
                } else {
                    TextRange(number.length)
                }
            )
        )
    }


    Column(modifier = Modifier.padding(SpaceMedium)) {
        Text(
            text = stringResource(id = R.string.enter_mobile),
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 12.sp
            )
        )
        Spacer(modifier = Modifier.height(SpaceSmall))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    color = if (isError) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(10.dp),
                    width = 1.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "+91",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.tertiary
                    )
                )
                Spacer(modifier = Modifier.width(SpaceSmall))

                BasicTextField(
                    modifier = Modifier
                        .focusRequester(focusRequester),
                    value = text,
                    onValueChange = { newText ->
                        if (newText.text.isBlank()) {
                            // If the new text is blank, reset the cursor to the start
                            text = newText.copy(text = "", selection = TextRange(0))
                        } else if (newText.text.length <= 10 && newText.text.all { it.isDigit() }) {
                            text = newText
                            onNumberChanged(newText.text)
                        } else {
                            // Limit to 10 digits
                            text = TextFieldValue(newText.text.take(10), TextRange(10))
                            onNumberChanged("")
                        }
                    },
                    visualTransformation = { input ->
                        val truncatedText = input.text.take(10)
                        TransformedText(
                            AnnotatedString(truncatedText),
                            object : OffsetMapping {
                                override fun originalToTransformed(offset: Int): Int {
                                    return offset.coerceAtMost(truncatedText.length)
                                }

                                override fun transformedToOriginal(offset: Int): Int {
                                    return offset.coerceAtMost(input.text.length)
                                }
                            }
                        )
                    },
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.tertiary),
                    singleLine = true,
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = MaterialTheme.colorScheme.tertiary
                    ),

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                )
            }
        }
        if (isError) {
            Spacer(modifier = Modifier.height(SpaceSmall))
            Text(
                text = stringResource(id = R.string.invalid_mobile_nmbr),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onError,
                    fontSize = 12.sp
                )
            )
        }
    }
}
