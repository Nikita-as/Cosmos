package ru.otus.cosmos.feature.planets.domain.interactor

import ru.otus.cosmos.feature.planets.domain.repository.PlanetsRepository
import javax.inject.Inject

class PlanetsInteractor @Inject constructor(
    private val repository: PlanetsRepository,
) {
    suspend fun getPlanets() = runCatching {
        repository.getPlanets()
    }
}