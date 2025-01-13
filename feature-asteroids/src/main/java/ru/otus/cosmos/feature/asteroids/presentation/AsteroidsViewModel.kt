package ru.otus.cosmos.feature.asteroids.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.otus.cosmos.common_base.utils.launchEvent
import ru.otus.cosmos.feature.asteroids.domain.interactor.AsteroidsInteractor
import javax.inject.Inject

@HiltViewModel
class AsteroidsViewModel @Inject constructor(
    private val interactor: AsteroidsInteractor,
) : ViewModel() {

    private val _viewState = MutableStateFlow<AsteroidsViewState>(AsteroidsViewState.Initial)
    val viewState: StateFlow<AsteroidsViewState> = _viewState.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    init {
        getAsteroids()
    }

    fun obtainAction(action: AsteroidsAction) {
        when (action) {
            is AsteroidsAction.BackClick -> {}
            is AsteroidsAction.TryAgainClick -> getAsteroids()
        }
    }

    private fun getAsteroids() {
        viewModelScope.launchEvent(_loadingState) {
            interactor.getAsteroids().onSuccess {
                _viewState.value = AsteroidsViewState.ShowAsteroids(
                    asteroids = it.nearEarthObjects.values.flatten(),
                )
            }.onFailure {
                _viewState.value = AsteroidsViewState.ErrorLoad
            }
        }
    }
}