package ru.otus.cosmos.feature.asteroids.data.repository

import kotlinx.coroutines.delay
import ru.otus.cosmos.feature.asteroids.domain.model.Asteroids
import ru.otus.cosmos.feature.asteroids.domain.model.CloseApproachData
import ru.otus.cosmos.feature.asteroids.domain.model.MissDistance
import ru.otus.cosmos.feature.asteroids.domain.model.NearEarthObject
import ru.otus.cosmos.feature.asteroids.domain.repository.AsteroidsRepository

class AsteroidsRepositoryImplMock : AsteroidsRepository {
    override suspend fun getAsteroids(): Asteroids {
        delay(500)
        return Asteroids(
            nearEarthObjects = mapOf(
                "2015-09-08" to listOf(
                    NearEarthObject(
                        id = "2465633",
                        name = "2465633",
                        closeApproachData = listOf(
                            CloseApproachData(
                                closeApproachDate = "2015-09-08",
                                missDistance = MissDistance(
                                    kilometers = "45290298",
                                )
                            )
                        ),
                        isPotentiallyHazardousAsteroid = true
                    ),
                    NearEarthObject(
                        id = "2465634",
                        name = "2465634",
                        closeApproachData = listOf(
                            CloseApproachData(
                                closeApproachDate = "2015-09-08",
                                missDistance = MissDistance(
                                    kilometers = "45295454",
                                )
                            )
                        ),
                        isPotentiallyHazardousAsteroid = false
                    ),
                    NearEarthObject(
                        id = "2465635",
                        name = "2465635",
                        closeApproachData = listOf(
                            CloseApproachData(
                                closeApproachDate = "2015-09-08",
                                missDistance = MissDistance(
                                    kilometers = "523525235",
                                )
                            )
                        ),
                        isPotentiallyHazardousAsteroid = false
                    ),
                    NearEarthObject(
                        id = "2465635",
                        name = "2465635",
                        closeApproachData = listOf(
                            CloseApproachData(
                                closeApproachDate = "2015-09-08",
                                missDistance = MissDistance(
                                    kilometers = "523525235",
                                )
                            )
                        ),
                        isPotentiallyHazardousAsteroid = false
                    ),
                    NearEarthObject(
                        id = "2465635",
                        name = "2465635",
                        closeApproachData = listOf(
                            CloseApproachData(
                                closeApproachDate = "2015-09-08",
                                missDistance = MissDistance(
                                    kilometers = "523525235",
                                )
                            )
                        ),
                        isPotentiallyHazardousAsteroid = false
                    ), NearEarthObject(
                        id = "2465635",
                        name = "2465635",
                        closeApproachData = listOf(
                            CloseApproachData(
                                closeApproachDate = "2015-09-08",
                                missDistance = MissDistance(
                                    kilometers = "523525235",
                                )
                            )
                        ),
                        isPotentiallyHazardousAsteroid = false
                    ), NearEarthObject(
                        id = "2465635",
                        name = "2465635",
                        closeApproachData = listOf(
                            CloseApproachData(
                                closeApproachDate = "2016-09-08",
                                missDistance = MissDistance(
                                    kilometers = "523525235",
                                )
                            )
                        ),
                        isPotentiallyHazardousAsteroid = false
                    ), NearEarthObject(
                        id = "2465635",
                        name = "2465635",
                        closeApproachData = listOf(
                            CloseApproachData(
                                closeApproachDate = "2015-03-08",
                                missDistance = MissDistance(
                                    kilometers = "523525235",
                                )
                            )
                        ),
                        isPotentiallyHazardousAsteroid = false
                    ), NearEarthObject(
                        id = "2465635",
                        name = "2465635",
                        closeApproachData = listOf(
                            CloseApproachData(
                                closeApproachDate = "2015-10-08",
                                missDistance = MissDistance(
                                    kilometers = "523525235",
                                )
                            )
                        ),
                        isPotentiallyHazardousAsteroid = false
                    ), NearEarthObject(
                        id = "2465635",
                        name = "2465635",
                        closeApproachData = listOf(
                            CloseApproachData(
                                closeApproachDate = "2015-09-18",
                                missDistance = MissDistance(
                                    kilometers = "523525235",
                                )
                            )
                        ),
                        isPotentiallyHazardousAsteroid = false
                    )
                )
            )
        )
    }
}