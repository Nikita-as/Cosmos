package ru.otus.cosmos.feature.asteroids.presentation

import ru.otus.cosmos.feature.asteroids.domain.model.NearEarthObject

sealed class AsteroidsViewState {

    data object Initial : AsteroidsViewState()
    class ShowAsteroids(val asteroids: List<NearEarthObject>) : AsteroidsViewState()
    data object ErrorLoad : AsteroidsViewState()

}