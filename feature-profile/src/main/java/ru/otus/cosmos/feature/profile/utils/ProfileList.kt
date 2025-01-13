package ru.otus.cosmos.feature.profile.utils

import ru.otus.cosmos.feature.profile.R
import ru.otus.cosmos.feature.profile.domain.model.ProfileItem
import ru.otus.cosmos.feature.profile.domain.model.ProfileItemType
import ru.otus.cosmos.common_ui.R as RCV

internal val profileList = listOf(
    ProfileItem(
        type = ProfileItemType.PHOTO_DAY, title = "Фотография дня", R.drawable.ic_photo
    ),
    ProfileItem(
        type = ProfileItemType.ASTEROIDS, title = "Астероиды рядом с нами", R.drawable.ic_acteroid
    ),
)