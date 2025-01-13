package ru.otus.cosmos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import ru.otus.cosmos.feature.asteroids.navigation.asteroidsScreen
import ru.otus.cosmos.feature.asteroids.navigation.navigateToAsteroids
import ru.otus.cosmos.feature.planets.navigation.PlanetsRoute
import ru.otus.cosmos.feature.planets.navigation.planetsScreen
import ru.otus.cosmos.feature.photo_day.navigation.navigateToPhotoDay
import ru.otus.cosmos.feature.photo_day.navigation.photoDayScreen
import ru.otus.cosmos.feature.profile.navigation.profileScreen

@Composable
fun CosmosNavHost(modifier: Modifier = Modifier, appState: NavControllerState) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = PlanetsRoute,
        modifier = modifier,
    ) {
        planetsScreen()
        profileScreen(
            onPhotoDayClick = navController::navigateToPhotoDay,
            onAsteroidsClick = navController::navigateToAsteroids,
        )
        photoDayScreen()
        asteroidsScreen()
    }
}