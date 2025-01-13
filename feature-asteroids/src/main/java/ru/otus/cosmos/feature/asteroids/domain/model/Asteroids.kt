package ru.otus.cosmos.feature.asteroids.domain.model

data class Asteroids(
    val nearEarthObjects: Map<String, List<NearEarthObject>>
)