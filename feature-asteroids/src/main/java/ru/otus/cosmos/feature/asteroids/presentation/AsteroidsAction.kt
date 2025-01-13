package ru.otus.cosmos.feature.asteroids.presentation

sealed class AsteroidsAction {

    class BackClick: AsteroidsAction()
    class TryAgainClick: AsteroidsAction()
}