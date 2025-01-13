package ru.otus.cosmos.feature.photo_day.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.otus.cosmos.common_base.utils.launchEvent
import ru.otus.cosmos.feature.photo_day.domain.interactor.PhotoDayInteractor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PhotoDayViewModel @Inject constructor(
    private val interactor: PhotoDayInteractor,
) : ViewModel() {

    private val _viewState = MutableStateFlow<PhotoDayViewState>(PhotoDayViewState.Initial)
    val viewState: StateFlow<PhotoDayViewState> = _viewState.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    init {
        getImageOfTheDay()
        getCurrentDate()
    }

    fun obtainAction(action: PhotoDayAction) {
        when (action) {
            is PhotoDayAction.BackClick -> {}
            is PhotoDayAction.TryAgainClick -> getImageOfTheDay()
        }
    }

    private fun getImageOfTheDay() {
        viewModelScope.launchEvent(_loadingState) {
            interactor.getPhotoDay().onSuccess {
                Log.d("--TAG", "getImageOfTheDay: ${it.image}")
                _viewState.value = PhotoDayViewState.ShowPicture(
                    image = it.image,
                    title = it.title,
                    explanation = it.explanation
                )
            }.onFailure {
                _viewState.value = PhotoDayViewState.ErrorLoad
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        Log.d("--TAG", "getCurrentDate: $dateFormat")
        return dateFormat.format(Date())
    }
}