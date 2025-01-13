package ru.otus.cosmos.common_ui.components.error

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.otus.cosmos.common_ui.R
import ru.otus.cosmos.common_ui.theme.CosmosBlue
import ru.otus.cosmos.common_ui.theme.CosmosDarkBlue
import ru.otus.cosmos.common_ui.theme.CosmosWhite

@Composable
internal fun ErrorLoadingScreen(
    modifier: Modifier = Modifier,
    errorClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = errorClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(CosmosBlue)
        ) {
            Text(
                text = stringResource(R.string.try_again),
                color = CosmosWhite
            )
        }
    }
}

@Preview
@Composable
private fun ErrorLoadingPreview() {
    ErrorLoadingScreen(errorClick = {})
}