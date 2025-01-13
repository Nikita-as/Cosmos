package ru.otus.cosmos.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.otus.cosmos.R
import ru.otus.cosmos.feature.planets.navigation.PlanetsRoute
import ru.otus.cosmos.feature.profile.navigation.ProfileRoute
import kotlin.reflect.KClass
import ru.otus.cosmos.common_ui.R as RCU

enum class BottomNavigation(
    @DrawableRes val iconId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
) {
    PLANETS(
        iconId = RCU.drawable.ic_earth_bottom,
        titleTextId = R.string.planet,
        route = PlanetsRoute::class,
    ),
    PROFILE(
        iconId = RCU.drawable.ic_cosmonaut,
        titleTextId = R.string.profile,
        route = ProfileRoute::class,
    ),
}