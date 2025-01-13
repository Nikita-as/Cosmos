package ru.otus.cosmos.feature.photo_day.domain.repository

import ru.otus.cosmos.feature.photo_day.domain.model.PhotoDay

interface PhotoDayRepository {

    suspend fun getPhotoDay(): PhotoDay


}