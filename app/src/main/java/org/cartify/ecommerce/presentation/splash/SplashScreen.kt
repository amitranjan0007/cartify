package org.cartify.ecommerce.presentation.splash

import android.window.SplashScreen
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.cartify.ecommerce.R
import org.cartify.ecommerce.feature_login.presentation.component.Button
import org.cartify.ecommerce.presentation.ui.theme.SpaceMedium
import org.cartify.ecommerce.presentation.ui.theme.SpaceSmall
import org.cartify.ecommerce.presentation.utils.Screen


@Composable
fun SplashScreen(onNavigate: (String) -> Unit = {}){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceSmall)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = R.drawable.romance,
                contentDescription = "Romance Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
        Spacer(modifier = Modifier.height(SpaceMedium))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SpaceMedium)) {

            Text(
                modifier = Modifier.padding(top = SpaceSmall),
                text = stringResource(id = R.string.splash_screen_title),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                ),

                )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                modifier = Modifier
                    .background(Color.Black),
                text = stringResource(id = R.string.login_mobile)
            ){
                onNavigate(Screen.LoginScreen.route)
            }

        }

    }
}