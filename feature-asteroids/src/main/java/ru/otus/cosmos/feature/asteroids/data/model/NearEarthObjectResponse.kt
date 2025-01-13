package ru.otus.cosmos.feature.asteroids.data.model

class NearEarthObjectResponse(
    val id: String? = null,
    val name: String? = null,
    val closeApproachData: List<CloseApproachDataResponse>? = null,
    val isPotentiallyHazardousAsteroid: Boolean? = null,
)