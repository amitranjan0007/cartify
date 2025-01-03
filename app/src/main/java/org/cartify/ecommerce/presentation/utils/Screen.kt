package org.cartify.ecommerce.presentation.utils

sealed class Screen(val route:String){
     data object SplashScreen:Screen("splash_screen")
     data object LoginScreen : Screen("login_screen")
     data object DashBoardScreen : Screen("dashboard_screen")
     data object BuyScreen : Screen("buy_screen")
     data object SellScreen : Screen("sell_screen")
     data object ProfileScreen : Screen("profile_screen")
     data object OtpScreen : Screen("otp_screen")
}