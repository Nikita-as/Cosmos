package ru.otus.cosmos.feature.photo_day.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.otus.cosmos.common_base.utils.IS_MOCK
import ru.otus.cosmos.feature.photo_day.data.api.PhotoDayApi
import ru.otus.cosmos.feature.photo_day.data.repository.PhotoDayRepositoryImpl
import ru.otus.cosmos.feature.photo_day.data.repository.PhotoDayRepositoryImplMock
import ru.otus.cosmos.feature.photo_day.domain.repository.PhotoDayRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhotoDayModule {

    @Provides
    @Singleton
    fun providePhotoDayRepository(
        repositoryImpl: PhotoDayRepositoryImpl
    ): PhotoDayRepository {
        return if (IS_MOCK.not()) {
            PhotoDayRepositoryImplMock()
        } else {
            repositoryImpl
        }
    }

    @Provides
    @Singleton
    fun providePhotoDayApi(retrofit: Retrofit): PhotoDayApi {
        return retrofit.create(PhotoDayApi::class.java)
    }
}