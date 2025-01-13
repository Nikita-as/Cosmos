package ru.otus.cosmos.common_base.shared_preference

interface SharedPreferencesMediator {
    var userName: String
    var currentDate: String
    fun changeUserName(name: String)
}