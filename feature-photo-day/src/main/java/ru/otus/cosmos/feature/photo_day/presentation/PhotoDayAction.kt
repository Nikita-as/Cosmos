package ru.otus.cosmos.feature.photo_day.presentation

sealed class PhotoDayAction {

    class BackClick: PhotoDayAction()
    class TryAgainClick: PhotoDayAction()
}