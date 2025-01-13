package ru.otus.cosmos.feature.planets.domain.repository

import ru.otus.cosmos.feature.planets.domain.model.Planets

interface PlanetsRepository {

    suspend fun getPlanets(): Planets

}