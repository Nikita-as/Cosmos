package ru.otus.cosmos.common_storage.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("photo_day")
data class PhotoDayLocal(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val title: String,
    val image: String,
    var info: String,
)