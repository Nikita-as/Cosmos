package ru.otus.cosmos.feature.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.otus.cosmos.common_ui.theme.CosmosBlack
import ru.otus.cosmos.common_ui.theme.CosmosDarkGray
import ru.otus.cosmos.common_ui.theme.CosmosTransparent
import ru.otus.cosmos.common_ui.theme.CosmosWhite
import ru.otus.cosmos.feature.profile.R
import ru.otus.cosmos.feature.profile.domain.model.ProfileItem
import ru.otus.cosmos.feature.profile.domain.model.ProfileItemType

@Composable
fun ProfileScreen(
    onPhotoDayClick: () -> Unit,
    onAsteroidsClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val itemClick = remember {
        { type: ProfileItemType ->
            when (type) {
                ProfileItemType.PHOTO_DAY -> onPhotoDayClick()
                ProfileItemType.ASTEROIDS -> onAsteroidsClick()
                ProfileItemType.UNKNOWN -> Unit
            }
        }
    }

    val onDoneClick = remember {
        { name: String ->
            viewModel.obtainAction(ProfileAction.ChangeNameClick(name))
        }
    }
    when (val state = viewState) {
        is ProfileState.ProfileInfo -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PaddingValues(top = 8.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(100.dp)
                )
                EditableName(onDoneClick, state.name)
                Spacer(modifier = Modifier.height(16.dp))
                state.profileItems.forEach {
                    ProfileItemView(it, itemClick)
                }
            }
        }
    }
}

@Composable
fun ProfileItemView(item: ProfileItem, itemClick: (ProfileItemType) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        color = CosmosWhite
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { itemClick(item.type) }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = CosmosBlack
            )
            Image(painterResource(item.image), contentDescription = null)
        }
    }
}

@Composable
fun EditableName(onDoneClick: (String) -> Unit, userName: String) {
    var name by remember { mutableStateOf(userName) }
    var isEditing by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(isEditing) {
        if (isEditing) {
            focusRequester.requestFocus() // Запрашиваем фокус, когда начинается редактирование
            keyboardController?.show() // Показываем клавиатуру
        }
    }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center // Центрируем содержимое внутри Box
    ) {
        if (isEditing) {
            // Поле для редактирования имени
            TextField(
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .align(Alignment.Center)
                    .testTag("TextField"),
                value = name,
                onValueChange = { name = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = CosmosTransparent,
                    unfocusedContainerColor = CosmosTransparent,
                    focusedIndicatorColor = CosmosTransparent,
                    unfocusedIndicatorColor = CosmosTransparent
                ),
                textStyle = MaterialTheme.typography.headlineMedium.copy(
                    textAlign = TextAlign.Center
                ),
                // Закрытие клавиатуры при нажатии клавиши "Ввод"
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        isEditing = false
                        onDoneClick(name)
                    }
                )
            )
        } else {
            // Отображение имени и иконки
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    modifier = Modifier.clickable { isEditing = true },
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit",
                    tint = CosmosDarkGray
                )
            }
        }
    }
}