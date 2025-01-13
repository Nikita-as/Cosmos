package ru.otus.cosmos.common_storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.otus.cosmos.common_storage.database.dao.PhotoDayDao
import ru.otus.cosmos.common_storage.database.dto.PhotoDayLocal

@Database(entities = [PhotoDayLocal::class], version = 1, exportSchema = false)
abstract class CosmosDatabase : RoomDatabase() {
    abstract fun characterDao(): PhotoDayDao
}