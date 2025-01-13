package ru.otus.cosmos.feature.photo_day.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import ru.otus.cosmos.common_ui.components.screen.BaseScreen
import ru.otus.cosmos.common_ui.theme.CosmosBlack
import ru.otus.cosmos.feature.photo_day.R

@Composable
fun PhotoDayScreen(viewModel: PhotoDayViewModel = hiltViewModel()) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val loadingState by viewModel.loadingState.collectAsStateWithLifecycle()
    val errorClick = remember { { viewModel.obtainAction(PhotoDayAction.TryAgainClick()) } }
    val scrollState = rememberScrollState()

    BaseScreen(
        isLoading = loadingState,
        isErrorLoading = viewState is PhotoDayViewState.ErrorLoad,
        errorClick = errorClick,
    ) {
        when (val state = viewState) {
            is PhotoDayViewState.ShowPicture -> {
                Column(
                    modifier = Modifier
                        .padding(PaddingValues(top = 8.dp, start = 16.dp, end = 16.dp))
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Text(
                        modifier = Modifier.testTag("TitleText"),
                        text = state.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = CosmosBlack
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp)),
                        model = state.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.ic_loading)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.padding(bottom = 16.dp),
                        text = state.explanation,
                        style = MaterialTheme.typography.bodyMedium,
                        color = CosmosBlack
                    )
                }
            }

            else -> Unit

        }
    }
}