package ru.otus.cosmos.feature.asteroids.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.otus.cosmos.common_base.utils.ANIMATION_DURATION
import ru.otus.cosmos.feature.asteroids.presentation.AsteroidsScreen

@Serializable
data object AsteroidsRoute

fun NavController.navigateToAsteroids(navOptions: NavOptions? = null) =
    navigate(AsteroidsRoute, navOptions)

fun NavGraphBuilder.asteroidsScreen() {
    composable<AsteroidsRoute>(
        enterTransition = {
            fadeIn(animationSpec = tween(durationMillis = ANIMATION_DURATION)) +
                    slideInVertically(
                        initialOffsetY = { fullHeight -> fullHeight / 4 },
                        animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing)
                    ) +
                    scaleIn(
                        initialScale = 0.9f,
                        animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing)
                    )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(durationMillis = ANIMATION_DURATION)) +
                    slideOutVertically(
                        targetOffsetY = { fullHeight -> -fullHeight / 4 },
                        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                    ) +
                    scaleOut(
                        targetScale = 1.1f,
                        animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing)
                    )
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(durationMillis = ANIMATION_DURATION)) +
                    slideInVertically(
                        initialOffsetY = { fullHeight -> -fullHeight / 4 },
                        animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing)
                    ) +
                    scaleIn(
                        initialScale = 0.9f,
                        animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing)
                    )
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(durationMillis = ANIMATION_DURATION)) +
                    slideOutVertically(
                        targetOffsetY = { fullHeight -> fullHeight / 4 },
                        animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing)
                    ) +
                    scaleOut(
                        targetScale = 1.1f,
                        animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = FastOutSlowInEasing)
                    )
        },
    ) {
        AsteroidsScreen()
    }
}