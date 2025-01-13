package ru.otus.cosmos.feature.photo_day.data.mapper

import ru.otus.cosmos.common_storage.database.dto.PhotoDayLocal
import ru.otus.cosmos.feature.photo_day.data.model.PhotoDayResponse
import ru.otus.cosmos.feature.photo_day.domain.model.PhotoDay
import javax.inject.Inject

class PhotoDayMapper @Inject constructor() {

    fun map(response: PhotoDayResponse) = PhotoDay(
        title = response.title.orEmpty(),
        image = response.url.orEmpty(),
        explanation = response.explanation.orEmpty()
    )

    fun map(response: PhotoDay) = PhotoDayLocal(
        title = response.title,
        image = response.image,
        info = response.explanation
    )

    fun map(response: PhotoDayLocal) = PhotoDay(
        title = response.title,
        image = response.image,
        explanation = response.info
    )
}