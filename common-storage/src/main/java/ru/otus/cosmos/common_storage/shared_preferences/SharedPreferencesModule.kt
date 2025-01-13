package ru.otus.cosmos.common_storage.shared_preferences

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.otus.cosmos.common_base.shared_preference.SharedPreferencesMediator

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    fun provideSharedPreferencesMediator(
        @ApplicationContext context: Context,
    ): SharedPreferencesMediator {
        return PhotoDayPreferences.newInstance(context)
    }
}