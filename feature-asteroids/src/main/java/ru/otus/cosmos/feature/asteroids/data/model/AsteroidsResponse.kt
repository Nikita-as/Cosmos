package ru.otus.cosmos.feature.asteroids.data.model

class AsteroidsResponse(
    val nearEarthObjects: Map<String, List<NearEarthObjectResponse>>? = null
)