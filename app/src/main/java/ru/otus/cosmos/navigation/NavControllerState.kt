package ru.otus.cosmos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import ru.otus.cosmos.feature.planets.navigation.navigateToPlanet
import ru.otus.cosmos.feature.profile.navigation.navigateToProfile
import ru.otus.cosmos.navigation.BottomNavigation.PLANETS
import ru.otus.cosmos.navigation.BottomNavigation.PROFILE
import kotlin.reflect.KClass

@Composable
fun rememberNavControllerState(
    navController: NavHostController = rememberNavController(),
): NavControllerState {
    return remember(navController) {
        NavControllerState(navController = navController)
    }
}

@Stable
class NavControllerState(val navController: NavHostController) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    val currentBottomDestination: BottomNavigation?
        @Composable get() {
            return BottomNavigation.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(route = topLevelDestination.route) == true
            }
        }

    val bottomDestinations: List<BottomNavigation> = BottomNavigation.entries

    fun navigateToBottomDestination(topLevelDestination: BottomNavigation) {
        val bottomNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            PLANETS -> navController.navigateToPlanet(bottomNavOptions)
            PROFILE -> navController.navigateToProfile(bottomNavOptions)
        }
    }
}

internal fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any { it.hasRoute(route) } ?: false