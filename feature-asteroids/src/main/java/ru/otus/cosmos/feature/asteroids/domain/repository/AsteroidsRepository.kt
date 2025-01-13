package ru.otus.cosmos.feature.asteroids.domain.repository

import ru.otus.cosmos.feature.asteroids.domain.model.Asteroids

interface AsteroidsRepository {

    suspend fun getAsteroids(): Asteroids

}