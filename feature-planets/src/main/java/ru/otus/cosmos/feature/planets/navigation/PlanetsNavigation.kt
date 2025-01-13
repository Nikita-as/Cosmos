package ru.otus.cosmos.feature.planets.navigation

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
    composable<PlanetsRoute> {
        PlanetsScreen()
    }
}