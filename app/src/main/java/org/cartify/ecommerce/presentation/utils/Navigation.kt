package org.cartify.ecommerce.presentation.utils

import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import org.cartify.ecommerce.feature_buy.presentation.ui.BuyScreen
import org.cartify.ecommerce.feature_home.presentation.ui.HomeScreen
import org.cartify.ecommerce.feature_home.presentation.viewmodel.HomeScreenViewModel
import org.cartify.ecommerce.feature_login.presentation.ui.otp.OtpScreen
import org.cartify.ecommerce.feature_login.presentation.ui.otp.viewmodel.OtpViewModel
import org.cartify.ecommerce.feature_login.presentation.ui.login.LoginScreen
import org.cartify.ecommerce.feature_profile.presentation.ui.ProfileScreen
import org.cartify.ecommerce.feature_sell_rent.presentation.SellScreen
import org.cartify.ecommerce.presentation.splash.SplashScreen

@Composable
fun Navigation(navHostController: NavHostController,snackbarHostState: SnackbarHostState){
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val otpViewModel: OtpViewModel = hiltViewModel()
    NavHost(navController = navHostController, startDestination = Screen.SplashScreen.route ){
        composable(route=Screen.SplashScreen.route, deepLinks = listOf(navDeepLink { uriPattern="https://cartify.com/test" })){
            SplashScreen(onNavigate = { navHostController.navigate(it) })
        }
       composable(route=Screen.LoginScreen.route, deepLinks = listOf(navDeepLink { uriPattern="https://cartify.com/test" })){
           LoginScreen(onNavigate = { navHostController.navigate(it) })
       }
        composable(route=Screen.BuyScreen.route, deepLinks = listOf(navDeepLink { uriPattern="https://cartify.com/test" })){
           BuyScreen()
        }
        composable(route=Screen.ProfileScreen.route, deepLinks = listOf(navDeepLink { uriPattern="https://cartify.com/test" })){
            ProfileScreen()
        }
        composable(route=Screen.SellScreen.route, deepLinks = listOf(navDeepLink { uriPattern="https://cartify.com/test" })){
            SellScreen()
        }
        composable(route=Screen.DashBoardScreen.route, deepLinks = listOf(navDeepLink { uriPattern="https://cartify.com/test" })){
            HomeScreen(viewModel = homeScreenViewModel,otpViewModel=otpViewModel)
        }

        composable(route=Screen.OtpScreen.route, deepLinks = listOf(navDeepLink { uriPattern="https://cartify.com/test" })){
            OtpScreen(viewModel = otpViewModel )
        }
    }
}