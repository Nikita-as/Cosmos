package ru.otus.cosmos.feature.profile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.otus.cosmos.common_base.providers.DispatcherProvider
import ru.otus.cosmos.common_base.shared_preference.SharedPreferencesMediator
import ru.otus.cosmos.feature.profile.data.repository.ProfileRepositoryImpl
import ru.otus.cosmos.feature.profile.domain.repository.ProfileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileRepository(
        dispatcherProvider: DispatcherProvider,
        sharedPreferencesMediator: SharedPreferencesMediator,
    ): ProfileRepository {
        return ProfileRepositoryImpl(dispatcherProvider, sharedPreferencesMediator)
    }
}