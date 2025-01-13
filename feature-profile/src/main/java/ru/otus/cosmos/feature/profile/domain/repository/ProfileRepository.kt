package ru.otus.cosmos.feature.profile.domain.repository

interface ProfileRepository {

    suspend fun changeUserName(userName: String)

}