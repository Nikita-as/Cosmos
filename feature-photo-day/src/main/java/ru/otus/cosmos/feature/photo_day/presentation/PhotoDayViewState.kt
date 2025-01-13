package ru.otus.cosmos.feature.photo_day.presentation

sealed class PhotoDayViewState {

    data object Initial : PhotoDayViewState()

    class ShowPicture(
        val image: String, val title: String, val explanation: String
    ) : PhotoDayViewState()

    data object ErrorLoad : PhotoDayViewState()

}