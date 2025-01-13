package ru.otus.cosmos.feature.planets.data.mapper

import ru.otus.cosmos.feature.planets.data.model.PlanetInfoResponse
import ru.otus.cosmos.feature.planets.data.model.PlanetsResponse
import ru.otus.cosmos.feature.planets.domain.model.PlanetInfo
import ru.otus.cosmos.feature.planets.domain.model.Planets
import javax.inject.Inject

class PlanetsMapper @Inject constructor() {

    fun map(response: PlanetsResponse) = Planets(
        planets = response.planets.orEmpty().map { map(it) }
    )

    private fun map(response: PlanetInfoResponse?) = PlanetInfo(
        id = response?.id ?: -1,
        icon = response?.icon ?: -1,
        name = response?.name.orEmpty(),
        info = response?.info.orEmpty(),
    )
}