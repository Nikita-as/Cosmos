package ru.otus.cosmos.common_base.database

interface PhotoDayRepository {
    suspend fun addInfo(title: String, image: String, info: String): Long
    suspend fun clearDatabase()
}