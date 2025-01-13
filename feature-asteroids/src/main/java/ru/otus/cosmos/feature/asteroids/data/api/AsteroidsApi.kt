package ru.otus.cosmos.feature.asteroids.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.otus.cosmos.feature.asteroids.data.model.AsteroidsResponse

interface AsteroidsApi {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): AsteroidsResponse
}