package ru.otus.cosmos.feature.planets.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.otus.cosmos.common_base.utils.launchEvent
import ru.otus.cosmos.feature.planets.domain.interactor.PlanetsInteractor
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val interactor: PlanetsInteractor,
) : ViewModel() {

    private val _viewState = MutableStateFlow<PlanetsViewState>(PlanetsViewState.Initial)
    val viewState: StateFlow<PlanetsViewState> = _viewState.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    init {
        getPlanets()
    }

    fun obtainAction(action: PlanetsAction) {
        when (action) {
            is PlanetsAction.TryAgainClick -> getPlanets()
        }
    }

    private fun getPlanets() {
        viewModelScope.launchEvent(_loadingState) {
            interactor.getPlanets().onSuccess {
                _viewState.value = PlanetsViewState.ShowPlanets(planets = it.planets)
            }.onFailure {
                _viewState.value = PlanetsViewState.ErrorLoad
            }
        }
    }
}