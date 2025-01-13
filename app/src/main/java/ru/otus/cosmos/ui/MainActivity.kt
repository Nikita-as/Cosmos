package ru.otus.cosmos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import ru.otus.cosmos.common_ui.theme.CosmosBackgroundColor
import ru.otus.cosmos.common_ui.theme.CosmosBlack
import ru.otus.cosmos.common_ui.theme.CosmosBlue
import ru.otus.cosmos.common_ui.theme.CosmosDarkBlue
import ru.otus.cosmos.common_ui.theme.CosmosTheme
import ru.otus.cosmos.common_ui.theme.CosmosTransparent
import ru.otus.cosmos.common_ui.theme.CosmosWhite
import ru.otus.cosmos.navigation.CosmosNavHost
import ru.otus.cosmos.navigation.NavControllerState
import ru.otus.cosmos.navigation.isRouteInHierarchy
import ru.otus.cosmos.navigation.rememberNavControllerState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT,
            )
        )
        setContent {
            val appState = rememberNavControllerState()
            CosmosTheme {
                MainScreen(appState)
            }
        }
    }
}

@Composable
fun MainScreen(appState: NavControllerState) {
    Scaffold(
        bottomBar = { BottomNavigationBar(appState) },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Top,
                        )
                    ),
            ) {
                CosmosNavHost(appState = appState)
            }
        },
        containerColor = CosmosBackgroundColor,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    )
}

@Composable
fun BottomNavigationBar(navControllerState: NavControllerState) {
    val currentDestination = navControllerState.currentDestination
    NavigationBar(containerColor = CosmosWhite) {
        navControllerState.bottomDestinations.forEach { destination ->
            val selected = currentDestination.isRouteInHierarchy(destination.route)
            NavigationBarItem(
                icon = {
                    Image(
                        painterResource(id = destination.iconId),
                        contentDescription = "Icon"
                    )
                },
                label = {
                    Text(
                        text = stringResource(destination.titleTextId),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = CosmosTransparent,
                    selectedTextColor = CosmosDarkBlue,
                    selectedIndicatorColor = CosmosBlue,
                    unselectedIconColor = CosmosTransparent,
                    unselectedTextColor = CosmosBlack,
                    disabledIconColor = CosmosTransparent,
                    disabledTextColor = CosmosTransparent,
                ),
                selected = selected,
                onClick = { navControllerState.navigateToBottomDestination(destination) },
            )
        }
    }
}