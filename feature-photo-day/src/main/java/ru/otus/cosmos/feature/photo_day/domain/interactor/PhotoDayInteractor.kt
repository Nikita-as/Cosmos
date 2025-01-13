package ru.otus.cosmos.feature.photo_day.domain.interactor

import ru.otus.cosmos.feature.photo_day.domain.repository.PhotoDayRepository
import javax.inject.Inject

class PhotoDayInteractor @Inject constructor(
    private val repository: PhotoDayRepository
) {
    suspend fun getPhotoDay() = runCatching {
        repository.getPhotoDay()
    }
}