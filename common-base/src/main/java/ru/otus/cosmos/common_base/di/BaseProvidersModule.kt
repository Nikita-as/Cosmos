package ru.otus.cosmos.common_base.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.otus.cosmos.common_base.providers.DispatcherProvider
import ru.otus.cosmos.common_base.providers.DispatcherProviderImpl
import ru.otus.cosmos.common_base.providers.StringProvider
import ru.otus.cosmos.common_base.providers.StringProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseProvidersModule {

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderImpl()
    }

    @Provides
    @Singleton
    fun provideStringProvider(@ApplicationContext context: Context): StringProvider {
        return StringProviderImpl(context)
    }
}