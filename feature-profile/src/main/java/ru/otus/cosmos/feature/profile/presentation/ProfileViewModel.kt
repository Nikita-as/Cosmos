package ru.otus.cosmos.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.otus.cosmos.common_base.shared_preference.SharedPreferencesMediator
import ru.otus.cosmos.common_base.utils.launchEvent
import ru.otus.cosmos.feature.profile.domain.interactor.ProfileInteractor
import ru.otus.cosmos.feature.profile.utils.profileList
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val interactor: ProfileInteractor,
    private val sharedPreferences: SharedPreferencesMediator,
) : ViewModel() {

    private val _viewState = MutableStateFlow<ProfileState>(setupState())
    val viewState: StateFlow<ProfileState> = _viewState.asStateFlow()

    fun obtainAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.ItemClick -> {}
            is ProfileAction.ChangeNameClick -> changeUserName(action.name)
        }
    }

    private fun changeUserName(name: String) {
        viewModelScope.launchEvent {
            interactor.changeUserName(name).onSuccess {
                _viewState.value = setupState()
            }
        }
    }

    private fun setupState() =
        ProfileState.ProfileInfo(
            profileItems = profileList,
            name = sharedPreferences.userName,
        )
}