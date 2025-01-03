package org.cartify.ecommerce.presentation.state
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItemUiState(
    val icon:ImageVector?=null,
    val notificationCount:Int,
    val route:String
)


