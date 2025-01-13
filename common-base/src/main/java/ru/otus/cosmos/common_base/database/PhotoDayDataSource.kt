package ru.otus.cosmos.common_base.database

interface PhotoDayDataSource {
    fun getAllInfo(): List<String>
    fun addInfo(title: String, image: String, info: String): Long
    fun clear()
}