package ru.otus.cosmos.common_test.mock

import ru.otus.cosmos.common_base.shared_preference.SharedPreferencesMediator

class SharedPreferencesFakeImpl() : SharedPreferencesMediator {

    private val prefMap = hashMapOf<String, Any>()

    override var userName: String
        get() = prefMap.getOrDefault(KEY_USER_NAME, "Имя") as String
        set(value) {
            prefMap[KEY_USER_NAME] = value
        }

    override var currentDate: String
        get() = prefMap.getOrDefault(KEY_CURRENT_DATE, "") as String
        set(value) {
            prefMap[KEY_CURRENT_DATE] = value
        }

    override fun changeUserName(name: String) {
        if (prefMap.containsKey(KEY_USER_NAME)) {
            prefMap[KEY_USER_NAME] = name
        }
    }

    companion object {

        private const val KEY_USER_NAME = "user_name"
        private const val KEY_CURRENT_DATE = "current_date"
    }
}