package ru.otus.cosmos.feature.profile.data.repository

import kotlinx.coroutines.withContext
import ru.otus.cosmos.common_base.providers.DispatcherProvider
import ru.otus.cosmos.common_base.shared_preference.SharedPreferencesMediator
import ru.otus.cosmos.feature.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val sharedPreferencesMediator: SharedPreferencesMediator,
) : ProfileRepository {

    override suspend fun changeUserName(userName: String) = withContext(dispatcherProvider.io()) {
        sharedPreferencesMediator.userName = userName
    }
}