package ru.otus.cosmos.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.otus.cosmos.feature.profile.presentation.ProfileScreen

@Serializable
data object ProfileRoute

fun NavController.navigateToProfile(navOptions: NavOptions) = navigate(ProfileRoute, navOptions)

fun NavGraphBuilder.profileScreen(
    onPhotoDayClick: () -> Unit,
    onAsteroidsClick: () -> Unit,
) {
    composable<ProfileRoute> {
        ProfileScreen(onPhotoDayClick = onPhotoDayClick, onAsteroidsClick = onAsteroidsClick)
    }
}