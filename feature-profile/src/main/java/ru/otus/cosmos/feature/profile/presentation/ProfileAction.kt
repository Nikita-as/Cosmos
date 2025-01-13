package ru.otus.cosmos.feature.profile.presentation

sealed class ProfileAction {
    class ItemClick(val itemId: Int) : ProfileAction()
    class ChangeNameClick(val name: String) : ProfileAction()
}