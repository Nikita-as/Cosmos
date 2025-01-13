package ru.otus.cosmos.feature.planets.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.otus.cosmos.common_base.providers.DispatcherProvider
import ru.otus.cosmos.common_base.utils.IS_MOCK
import ru.otus.cosmos.feature.planets.data.api.PlanetsApi
import ru.otus.cosmos.feature.planets.data.mapper.PlanetsMapper
import ru.otus.cosmos.feature.planets.data.repository.PlanetsRepositoryImpl
import ru.otus.cosmos.feature.planets.data.repository.PlanetsRepositoryImplMock
import ru.otus.cosmos.feature.planets.domain.repository.PlanetsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlanetsModule {

    @Provides
    @Singleton
    fun providePlanetsRepository(
        repositoryImpl: PlanetsRepositoryImpl
    ): PlanetsRepository {
        return if (IS_MOCK) {
            PlanetsRepositoryImplMock()
        } else {
            repositoryImpl
        }
    }

    @Provides
    @Singleton
    fun providePlanetsApi(retrofit: Retrofit): PlanetsApi {
        return retrofit.create(PlanetsApi::class.java)
    }
}