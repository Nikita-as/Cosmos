package ru.otus.cosmos.feature.asteroids.domain.interactor

import ru.otus.cosmos.feature.asteroids.domain.repository.AsteroidsRepository
import javax.inject.Inject

class AsteroidsInteractor @Inject constructor(
    private val repository: AsteroidsRepository,
) {
    suspend fun getAsteroids() = runCatching { repository.getAsteroids() }
}