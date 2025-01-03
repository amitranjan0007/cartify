package org.cartify.ecommerce.feature_home.presentation.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.cartify.ecommerce.feature_home.presentation.viewmodel.HomeScreenViewModel
import org.cartify.ecommerce.feature_login.presentation.ui.otp.OtpScreen
import org.cartify.ecommerce.feature_login.presentation.ui.otp.viewmodel.OtpViewModel


@Composable
fun HomeScreen(modifier: Modifier=Modifier,viewModel: HomeScreenViewModel,otpViewModel: OtpViewModel){
     val state= viewModel._state.collectAsState()
    Box(modifier = modifier.fillMaxSize()) {
       OtpScreen(modifier = modifier, viewModel = otpViewModel)
    }

}