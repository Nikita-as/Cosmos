package ru.otus.cosmos.feature.photo_day.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.otus.cosmos.feature.photo_day.data.model.PhotoDayResponse

interface PhotoDayApi {
    @GET("planetary/apod")
    suspend fun getImageOfTheDay(@Query("api_key") apiKey: String): PhotoDayResponse
}