package ru.otus.cosmos.common_storage.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.otus.cosmos.common_storage.database.dto.PhotoDayLocal

@Dao
interface PhotoDayDao {
    @Query("SELECT * FROM photo_day")
    suspend fun getPhotoDayInfo(): PhotoDayLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: PhotoDayLocal)

    @Delete
    suspend fun delete(character: PhotoDayLocal)
}