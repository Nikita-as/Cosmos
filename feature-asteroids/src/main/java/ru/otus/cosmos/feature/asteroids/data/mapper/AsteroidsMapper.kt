package ru.otus.cosmos.feature.asteroids.data.mapper

import ru.otus.cosmos.feature.asteroids.data.model.AsteroidsResponse
import ru.otus.cosmos.feature.asteroids.data.model.CloseApproachDataResponse
import ru.otus.cosmos.feature.asteroids.data.model.MissDistanceResponse
import ru.otus.cosmos.feature.asteroids.data.model.NearEarthObjectResponse
import ru.otus.cosmos.feature.asteroids.domain.model.Asteroids
import ru.otus.cosmos.feature.asteroids.domain.model.CloseApproachData
import ru.otus.cosmos.feature.asteroids.domain.model.MissDistance
import ru.otus.cosmos.feature.asteroids.domain.model.NearEarthObject
import javax.inject.Inject

class AsteroidsMapper @Inject constructor() {

    fun map(response: AsteroidsResponse) = Asteroids(
        nearEarthObjects = response.nearEarthObjects?.mapValues { entry ->
            entry.value.map { map(it) }
        } ?: emptyMap()
    )


    private fun map(response: NearEarthObjectResponse?) = NearEarthObject(
        id = response?.id.orEmpty(),
        name = response?.id.orEmpty(),
        closeApproachData = response?.closeApproachData.orEmpty().map { map(it) },
        isPotentiallyHazardousAsteroid = response?.isPotentiallyHazardousAsteroid ?: false,
    )

    private fun map(response: CloseApproachDataResponse?) = CloseApproachData(
        closeApproachDate = response?.closeApproachDate.orEmpty(),
        missDistance = map(response?.missDistance),
    )

    private fun map(response: MissDistanceResponse?) = MissDistance(
        kilometers = response?.kilometers.orEmpty().split(".")[0],
    )
}