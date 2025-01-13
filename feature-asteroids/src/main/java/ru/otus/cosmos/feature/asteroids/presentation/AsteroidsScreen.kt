package ru.otus.cosmos.feature.asteroids.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.otus.cosmos.common_ui.components.screen.BaseScreen
import ru.otus.cosmos.common_ui.theme.CosmosBlack
import ru.otus.cosmos.common_ui.theme.CosmosWhite
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun AsteroidsScreen(viewModel: AsteroidsViewModel = hiltViewModel()) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val loadingState by viewModel.loadingState.collectAsStateWithLifecycle()
    val errorClick = remember { { viewModel.obtainAction(AsteroidsAction.TryAgainClick()) } }

    BaseScreen(
        isLoading = loadingState,
        isErrorLoading = viewState is AsteroidsViewState.ErrorLoad,
        errorClick = errorClick,
    ) {
        when (val state = viewState) {
            is AsteroidsViewState.ShowAsteroids -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(PaddingValues(top = 8.dp))
                ) {
                    items(state.asteroids) { asteroid ->
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = CosmosWhite),
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    text = "Название астероида: ${asteroid.name}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = CosmosBlack
                                )
                                asteroid.closeApproachData.forEach { approach ->
                                    Text(
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        text = "Дата приближения: ${approach.closeApproachDate.parseDate()}",
                                        color = CosmosBlack
                                    )
                                    Text(
                                        modifier = Modifier.padding(bottom = 4.dp),
                                        text = "Расстояние до Земли: ${approach.missDistance.kilometers} км",
                                        color = CosmosBlack
                                    )
                                    Text(
                                        modifier = Modifier.padding(bottom = 4.dp),
                                        text = "Опасность для земли: ${if (asteroid.isPotentiallyHazardousAsteroid) "Да" else "Нет"}",
                                        color = CosmosBlack
                                    )
                                }
                            }
                        }
                    }
                }
            }

            else -> Unit
        }
    }
}

private fun String.parseDate(): String {
    val date = LocalDate.parse(this) // Парсим строку в дату
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'года'", Locale("ru"))
    return date.format(formatter)
}