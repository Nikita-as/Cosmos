package ru.otus.cosmos.feature.planets.data.api

import retrofit2.http.GET
import ru.otus.cosmos.feature.planets.data.model.PlanetsResponse

interface PlanetsApi {

    @GET("get-planets")
    suspend fun getPlanets(): PlanetsResponse
}