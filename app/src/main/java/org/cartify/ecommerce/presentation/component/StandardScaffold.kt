package org.cartify.ecommerce.presentation.component
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.cartify.ecommerce.presentation.state.BottomNavItemUiState
import org.cartify.ecommerce.presentation.utils.Screen


val bottomItem= listOf<BottomNavItemUiState>(
    BottomNavItemUiState(
        icon = Icons.Default.Home,
        notificationCount = 0,
        route = Screen.DashBoardScreen.route
    ),
    BottomNavItemUiState(
        icon = Icons.Default.Warning,
        notificationCount = 0,
        route = Screen.BuyScreen.route
    ),
    BottomNavItemUiState(
        icon = Icons.Default.AddCircle,
        notificationCount = 0,
        route = Screen.SellScreen.route
    ),
    BottomNavItemUiState(
        icon = Icons.Default.Person,
        notificationCount = 0,
        route = Screen.BuyScreen.route
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardScaffold(
    modifier: Modifier=Modifier,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    showBottomBar:Boolean=true,
    bottomNavItems:List<BottomNavItemUiState>?=bottomItem,
    content: @Composable ()->Unit
) {
    Scaffold(
        modifier=modifier,
        snackbarHost= {  },
        bottomBar = {
//            if(showBottomBar){
//                BottomAppBar(
//                    modifier = modifier.fillMaxWidth(),
//                    tonalElevation = 5.dp,
//                    containerColor = MaterialTheme.colorScheme.background
//                ) {
//                    val selectedColor= MaterialTheme.colorScheme.primary
//                    val unSelectedColor= MaterialTheme.colorScheme.onTertiary
//                    NavigationBar(modifier = modifier.fillMaxWidth()) {
//                        bottomNavItems?.forEachIndexed{i,bottomNavItem->
//                            val selected=bottomNavItem.route==navController.currentDestination?.route
//                            val animLength= animateFloatAsState(
//                                targetValue = if(selected) 1f else 0f,
//                                animationSpec= tween(
//                                    durationMillis = 200
//                                )
//                            )
//                            NavigationBarItem(
//                                modifier =  modifier.background(MaterialTheme.colorScheme.primary).height(40.dp),
//                                selected = bottomNavItem.route==navController.currentDestination?.route ,
//
//                                onClick = {
//                                    if(bottomNavItem.route!=navController.currentDestination?.route){
//                                        navController.navigate(bottomNavItem.route)
//                                    }
//                                },
//                                icon = {
//                                    Box(modifier = modifier
//                                        .fillMaxSize()
//                                        .padding(10.dp)
//                                        .drawBehind {
//                                            if (selected) {
//                                                if (animLength.value > 0) {
//                                                    drawLine(
//                                                        color = if (selected) selectedColor else unSelectedColor,
//                                                        start = Offset(
//                                                            (size.width / 2f) - animLength.value * 15.dp.toPx(),
//                                                            size.height
//                                                        ),
//                                                        end = Offset(
//                                                            (size.width / 2f) + animLength.value * 15.dp.toPx(),
//                                                            size.height
//                                                        ),
//                                                        strokeWidth = 5.dp.toPx(),
//                                                        cap = StrokeCap.Round
//                                                    )
//                                                }
//
//                                            }
//
//                                        }
//
//                                    ) {
//                                        if (bottomNavItem.icon != null) {
//                                            Icon(
//                                                modifier = modifier.align(Alignment.Center),
//                                                imageVector = bottomNavItem.icon,
//                                                contentDescription = ""
//                                            )
//
//                                        }
//                                    }
//                                }
//                            )
//                        }
//                    }
//                }
//            }
        }
    ){
        content()
    }
}