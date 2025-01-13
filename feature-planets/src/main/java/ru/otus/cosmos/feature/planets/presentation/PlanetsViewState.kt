package ru.otus.cosmos.feature.planets.presentation

import ru.otus.cosmos.feature.planets.domain.model.PlanetInfo

sealed class PlanetsViewState {

    data object Initial : PlanetsViewState()

    class ShowPlanets(
        val planets: List<PlanetInfo>,
    ) : PlanetsViewState()

    data object ErrorLoad : PlanetsViewState()

}