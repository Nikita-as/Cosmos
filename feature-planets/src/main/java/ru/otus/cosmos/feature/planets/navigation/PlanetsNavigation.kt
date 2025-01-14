package ru.otus.cosmos.feature.planets.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.otus.cosmos.feature.planets.presentation.PlanetsScreen

@Serializable
data object PlanetsRoute

fun NavController.navigateToPlanet(navOptions: NavOptions) = navigate(PlanetsRoute, navOptions)

fun NavGraphBuilder.planetsScreen() {
    composable<PlanetsRoute>(
        enterTransition = { fadeIn(animationSpec = tween(300)) }, // Анимация входа
        exitTransition = { fadeOut(animationSpec = tween(300)) }
    ) {
        PlanetsScreen()
    }
}