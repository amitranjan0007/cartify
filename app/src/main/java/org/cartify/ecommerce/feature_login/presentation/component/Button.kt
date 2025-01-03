package org.cartify.ecommerce.feature_login.presentation.component

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.cartify.ecommerce.R
import org.cartify.ecommerce.presentation.ui.theme.SpaceMedium


@Composable
fun Button(modifier: Modifier=Modifier,text:String,onClick:()->Unit={}){
    Box(modifier = modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.tertiary)
        .clip(RoundedCornerShape(10.dp)).clickable { onClick() },
        contentAlignment = Alignment.Center,


    ){
        Text(modifier = Modifier.padding(vertical = SpaceMedium),
            textAlign = TextAlign.Center,
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary
            ))
    }
}