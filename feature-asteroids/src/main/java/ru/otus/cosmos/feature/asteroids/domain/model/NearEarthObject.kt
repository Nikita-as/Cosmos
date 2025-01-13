package ru.otus.cosmos.feature.asteroids.domain.model

data class NearEarthObject(
    val id: String,
    val name: String,
    val closeApproachData: List<CloseApproachData>,
    val isPotentiallyHazardousAsteroid: Boolean,
)