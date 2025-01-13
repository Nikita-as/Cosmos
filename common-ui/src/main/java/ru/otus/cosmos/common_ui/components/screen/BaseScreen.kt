package ru.otus.cosmos.common_ui.components.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.otus.cosmos.common_ui.components.error.ErrorLoadingScreen
import ru.otus.cosmos.common_ui.components.loader.LoadingScreen

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isErrorLoading: Boolean = false,
    errorClick: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {},
) {
    Column(modifier) {
        if (isErrorLoading) {
            ErrorLoadingScreen(errorClick = errorClick)
        } else {
            content()
        }
    }
    if (isLoading) {
        LoadingScreen()
    }
}