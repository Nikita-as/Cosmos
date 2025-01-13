package ru.otus.cosmos.feature.asteroids.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.otus.cosmos.common_base.providers.DispatcherProvider
import ru.otus.cosmos.common_base.utils.IS_MOCK
import ru.otus.cosmos.feature.asteroids.data.api.AsteroidsApi
import ru.otus.cosmos.feature.asteroids.data.mapper.AsteroidsMapper
import ru.otus.cosmos.feature.asteroids.data.repository.AsteroidsRepositoryImpl
import ru.otus.cosmos.feature.asteroids.data.repository.AsteroidsRepositoryImplMock
import ru.otus.cosmos.feature.asteroids.domain.repository.AsteroidsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AsteroidsModule {

    @Provides
    @Singleton
    fun provideAsteroidsRepository(
        api: AsteroidsApi,
        mapper: AsteroidsMapper,
        dispatcherProvider: DispatcherProvider,
    ): AsteroidsRepository {
        return if (IS_MOCK) {
            AsteroidsRepositoryImplMock()
        } else {
            AsteroidsRepositoryImpl(api, mapper, dispatcherProvider)
        }
    }

    @Provides
    @Singleton
    fun provideAsteroidsApi(retrofit: Retrofit): AsteroidsApi {
        return retrofit.create(AsteroidsApi::class.java)
    }
}