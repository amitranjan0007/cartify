package org.cartify.ecommerce.presentation

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import dagger.hilt.android.AndroidEntryPoint
import org.cartify.ecommerce.presentation.component.StandardScaffold

import org.cartify.ecommerce.presentation.ui.theme.CartifyTheme
import org.cartify.ecommerce.presentation.utils.Navigation
import org.cartify.ecommerce.presentation.utils.Screen
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //option+cmd+L--format
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("FCM TOKEN At LoginScreen" + sharedPreferences.getString("fcm_token", ""))
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CartifyTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize().background(Color.White),
                ) {
                    val navController = rememberNavController()
                    val snackbarHostState = remember {
                        SnackbarHostState()
                    }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding() // Applies padding for the status bar and navigation bar
                    ) {
                        StandardScaffold(
                            modifier = Modifier,
                            navController = navController,
                            snackbarHostState = snackbarHostState,
                            showBottomBar = navBackStackEntry?.destination?.route in listOf(
                                Screen.DashBoardScreen.route,
                                Screen.BuyScreen.route,
                                Screen.SellScreen.route,
                                Screen.ProfileScreen.route
                            )
                        ) {
                            Navigation(
                                navHostController = navController,
                                snackbarHostState = snackbarHostState
                            )
                        }
                    }

                }
            }
        }
    }
}

