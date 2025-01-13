package ru.otus.cosmos.feature.planets.domain.model

data class PlanetInfo(
    val id: Int,
    val icon: Int,
    val name: String,
    val info: String,
)