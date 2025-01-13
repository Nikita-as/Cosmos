package ru.otus.cosmos.common_ui.components.toolbar

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import ru.otus.cosmos.common_ui.R

@Immutable
data class ToolbarState(
    val title: String,
    val navIcon: Icon = Icon.EMPTY,
) {
    enum class Icon(
        @DrawableRes
        val navigationIconId: Int?,
    ) {
        BACK(
            R.drawable.ic_back,
        ),
        EMPTY(null)
    }
}