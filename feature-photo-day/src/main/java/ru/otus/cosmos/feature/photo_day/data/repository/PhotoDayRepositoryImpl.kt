package ru.otus.cosmos.feature.photo_day.data.repository

import kotlinx.coroutines.withContext
import ru.otus.cosmos.common_base.providers.DispatcherProvider
import ru.otus.cosmos.common_base.shared_preference.SharedPreferencesMediator
import ru.otus.cosmos.common_base.utils.API_KEY
import ru.otus.cosmos.common_base.utils.getCurrentDate
import ru.otus.cosmos.common_storage.database.dao.PhotoDayDao
import ru.otus.cosmos.feature.photo_day.data.api.PhotoDayApi
import ru.otus.cosmos.feature.photo_day.data.mapper.PhotoDayMapper
import ru.otus.cosmos.feature.photo_day.domain.model.PhotoDay
import ru.otus.cosmos.feature.photo_day.domain.repository.PhotoDayRepository
import javax.inject.Inject

class PhotoDayRepositoryImpl @Inject constructor(
    private val api: PhotoDayApi,
    private val mapper: PhotoDayMapper,
    private val dispatcherProvider: DispatcherProvider,
    private val dao: PhotoDayDao,
    private val sharedPreferences: SharedPreferencesMediator
) : PhotoDayRepository {

    override suspend fun getPhotoDay(): PhotoDay = withContext(dispatcherProvider.io()) {
        val currentDate = getCurrentDate()
        if (sharedPreferences.currentDate.isEmpty()) {
            sharedPreferences.currentDate = getCurrentDate()
            val response = mapper.map(api.getImageOfTheDay(API_KEY))
            dao.insert(mapper.map(response))
            return@withContext response
        }
        if (sharedPreferences.currentDate == currentDate) {
            return@withContext getLocalPhotoDay()
        }
        val response = mapper.map(api.getImageOfTheDay(API_KEY))
        dao.insert(mapper.map(response))
        response
    }

    private suspend fun getLocalPhotoDay(): PhotoDay = mapper.map(dao.getPhotoDayInfo())
}