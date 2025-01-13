package ru.otus.cosmos.feature.profile.domain.model

data class ProfileItem(
    val type: ProfileItemType,
    val title: String,
    val image: Int
)