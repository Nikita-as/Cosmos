package ru.otus.cosmos.feature.profile.presentation

import ru.otus.cosmos.feature.profile.domain.model.ProfileItem

sealed class ProfileState {

    data class ProfileInfo(
        val profileItems: List<ProfileItem>,
        val name: String,
    ) : ProfileState()

}