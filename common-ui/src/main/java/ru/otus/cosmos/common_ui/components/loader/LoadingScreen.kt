package ru.otus.cosmos.common_ui.components.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.otus.cosmos.common_ui.theme.CosmosBackgroundColor
import ru.otus.cosmos.common_ui.theme.LoaderColor

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = CosmosBackgroundColor),
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            CircularProgressIndicator(
                color = LoaderColor,
            )
        }
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    LoadingScreen()
}