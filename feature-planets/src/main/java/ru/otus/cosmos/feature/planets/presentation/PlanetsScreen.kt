package ru.otus.cosmos.feature.planets.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import ru.otus.cosmos.common_ui.components.screen.BaseScreen
import ru.otus.cosmos.common_ui.theme.CosmosBlack
import ru.otus.cosmos.common_ui.theme.CosmosWhite
import ru.otus.cosmos.feature.R
import ru.otus.cosmos.feature.planets.domain.model.PlanetInfo

@Composable
fun PlanetsScreen(viewModel: PlanetsViewModel = hiltViewModel()) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val loadingState by viewModel.loadingState.collectAsStateWithLifecycle()
    val errorClick = remember { { viewModel.obtainAction(PlanetsAction.TryAgainClick()) } }

    BaseScreen(
        isLoading = loadingState,
        isErrorLoading = viewState is PlanetsViewState.ErrorLoad,
        errorClick = errorClick,
    ) {
        when (val state = viewState) {
            is PlanetsViewState.ShowPlanets -> {
                CardsPagerScreen(
                    modifier = Modifier.padding(PaddingValues(top = 8.dp)), state.planets
                )
            }

            else -> Unit

        }
    }
}

@Composable
fun CardsPagerScreen(modifier: Modifier, planets: List<PlanetInfo>) {
    val pagerState = rememberPagerState(initialPage = 2, pageCount = { planets.size })

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        pageSpacing = 8.dp,
    ) { page ->
        CardItem(planet = planets[page])
    }
}

@Composable
fun CardItem(planet: PlanetInfo) {

    var isFlipping by remember { mutableStateOf(false) }
    var isFrontVisible by remember { mutableStateOf(true) }
    val rotationStateCard = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    var rotationState by remember { mutableFloatStateOf(0f) }
    val animatedRotation by animateFloatAsState(
        targetValue = rotationState,
        animationSpec = tween(durationMillis = 3000)
    )
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = null
            ) {
                coroutineScope.launch {
                    if (!isFlipping) {
                        isFlipping = true
                        isFrontVisible = !isFrontVisible
                        rotationStateCard.animateTo(
                            targetValue = if (isFrontVisible) 0f else -180f,
                            animationSpec = tween(durationMillis = 800)
                        )
                        isFlipping = false
                    }
                }

            }
            .graphicsLayer {
                // Применяем 3D вращение
                rotationY = rotationStateCard.value
                cameraDistance = 22 * density // Задаем глубину камеры для 3D эффекта
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = CosmosWhite)
    ) {
        AnimatedVisibility(
            visible = isFrontVisible,
            modifier = Modifier.fillMaxSize(),
            enter = fadeIn(animationSpec = tween(durationMillis = 300)),
            exit = fadeOut(animationSpec = tween(durationMillis = 300))
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = planet.icon),
                        contentDescription = "Иконка",
                        modifier = Modifier
                            .size(300.dp)
                            .graphicsLayer {
                                rotationZ = animatedRotation // Применяем анимацию вращения
                            }
                            .clickable(
                                onClick = {
                                    rotationState += 180f // Увеличиваем угол вращения при клике
                                },
                                indication = null, // Убираем эффект нажатия
                                interactionSource = remember { MutableInteractionSource() })
                    )
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = planet.name,
                        style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
                        color = CosmosBlack
                    )
                }
                Image(
                    modifier = Modifier
                        .padding(bottom = 16.dp, end = 16.dp)
                        .size(40.dp)
                        .align(Alignment.BottomEnd),
                    painter = painterResource(R.drawable.ic_click),
                    contentDescription = null
                )
            }
        }
        AnimatedVisibility(
            visible = !isFrontVisible,
            modifier = Modifier.fillMaxSize(),
            enter = fadeIn(animationSpec = tween(durationMillis = 300)),
            exit = fadeOut(animationSpec = tween(durationMillis = 300))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = planet.info,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .graphicsLayer {
                            rotationY = 180f
                        },
                    color = CosmosBlack
                )
            }
        }
    }
}