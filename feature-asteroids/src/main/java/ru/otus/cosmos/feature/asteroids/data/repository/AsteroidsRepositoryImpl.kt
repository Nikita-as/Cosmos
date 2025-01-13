package ru.otus.cosmos.feature.asteroids.data.repository

import android.util.Log
import kotlinx.coroutines.withContext
import ru.otus.cosmos.common_base.providers.DispatcherProvider
import ru.otus.cosmos.common_base.utils.API_KEY
import ru.otus.cosmos.feature.asteroids.data.api.AsteroidsApi
import ru.otus.cosmos.feature.asteroids.data.mapper.AsteroidsMapper
import ru.otus.cosmos.feature.asteroids.domain.model.Asteroids
import ru.otus.cosmos.feature.asteroids.domain.repository.AsteroidsRepository

class AsteroidsRepositoryImpl(
    private val api: AsteroidsApi,
    private val mapper: AsteroidsMapper,
    private val dispatcherProvider: DispatcherProvider,
) : AsteroidsRepository {

    override suspend fun getAsteroids(): Asteroids = withContext(dispatcherProvider.io()) {
        Log.d("--TAG", "getAsteroids: ")
        mapper.map(api.getAsteroids(startDate = "2015-09-07", endDate = "2015-09-08", API_KEY))
    }
}