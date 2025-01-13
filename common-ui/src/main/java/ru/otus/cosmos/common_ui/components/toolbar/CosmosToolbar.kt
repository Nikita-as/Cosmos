package ru.otus.cosmos.common_ui.components.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.otus.cosmos.common_ui.theme.CosmosTransparent

@Composable
fun CosmosToolbar(toolbarTitle: String) {
    Text(
        text = toolbarTitle,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = CosmosTransparent),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
    )
}