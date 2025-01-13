package ru.otus.cosmos.feature.planets.data.repository

import kotlinx.coroutines.withContext
import ru.otus.cosmos.common_base.providers.DispatcherProvider
import ru.otus.cosmos.feature.planets.data.api.PlanetsApi
import ru.otus.cosmos.feature.planets.data.mapper.PlanetsMapper
import ru.otus.cosmos.feature.planets.domain.model.Planets
import ru.otus.cosmos.feature.planets.domain.repository.PlanetsRepository
import javax.inject.Inject

class PlanetsRepositoryImpl @Inject constructor(
    private val api: PlanetsApi,
    private val mapper: PlanetsMapper,
    private val dispatcherProvider: DispatcherProvider,
) : PlanetsRepository {
    override suspend fun getPlanets(): Planets = withContext(dispatcherProvider.io()) {
        mapper.map(api.getPlanets())
    }
}