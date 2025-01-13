package ru.otus.cosmos.feature.profile.domain.interactor

import ru.otus.cosmos.feature.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend fun changeUserName(userName: String) = runCatching {
        repository.changeUserName(userName)
    }
}