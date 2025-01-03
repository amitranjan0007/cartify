package org.cartify.ecommerce.feature_login.presentation.ui.login

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.cartify.ecommerce.R
import org.cartify.ecommerce.presentation.ui.theme.SpaceSmall
import coil.compose.AsyncImage
import org.cartify.ecommerce.common.ui.isMobileNumberValid
import org.cartify.ecommerce.feature_login.presentation.component.Button
import org.cartify.ecommerce.presentation.component.StandardTextField
import org.cartify.ecommerce.presentation.ui.theme.ButtonHeightMedium
import org.cartify.ecommerce.presentation.ui.theme.SpaceLarge
import org.cartify.ecommerce.presentation.ui.theme.SpaceMedium
import org.cartify.ecommerce.presentation.utils.Screen

@Composable
fun LoginScreen(onNavigate: (String) -> Unit = {},modifier: Modifier=Modifier) {
    //   println("FCM TOKEN At LoginScreen"+sharedPreferences.getString("fcm_token",""))
    var mobileNumber by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SpaceSmall)
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(modifier = Modifier) {
            Text(
                text = stringResource(id = R.string.login_mobile),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                number = null,
                isError = isError,
                focusRequester = remember {
                    FocusRequester()
                },
                onNumberChanged = {
                    newNumber -> mobileNumber = newNumber?:""
                    isError=false
                                  },
            )
        }
        Column(modifier = Modifier) {
            Button(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.tertiary)
                    .height(
                        ButtonHeightMedium
                    ),
                text = stringResource(id = R.string.log_in)
            ) {
                val isValid=mobileNumber.isMobileNumberValid()
                if(isValid){
                    onNavigate(Screen.OtpScreen.route)
                }else{
                    isError=true
                }

            }
        }
    }

}
