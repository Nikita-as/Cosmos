package ru.otus.cosmos.common_storage.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.otus.cosmos.common_storage.database.CosmosDatabase
import ru.otus.cosmos.common_storage.database.dao.PhotoDayDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideCosmosDatabase(
        @ApplicationContext context: Context
    ): CosmosDatabase = Room.databaseBuilder(context, CosmosDatabase::class.java, "cosmos.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providePhotoDayDao(marvelDatabase: CosmosDatabase): PhotoDayDao =
        marvelDatabase.characterDao()
}