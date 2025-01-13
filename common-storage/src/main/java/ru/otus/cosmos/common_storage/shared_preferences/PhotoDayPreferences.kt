package ru.otus.cosmos.common_storage.shared_preferences

import android.content.Context
import androidx.core.content.edit
import ru.otus.cosmos.common_base.shared_preference.SharedPreferencesMediator

class PhotoDayPreferences(appContext: Context) : SharedPreferencesMediator {

    private val sharedPreferences =
        appContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    override var userName: String
        get() = sharedPreferences.getString(KEY_USER_NAME, "Имя") ?: "Имя"
        set(value) = sharedPreferences.edit { putString(KEY_USER_NAME, value) }

    override var currentDate: String
        get() = sharedPreferences.getString(KEY_CURRENT_DATE, "") ?: ""
        set(value) = sharedPreferences.edit { putString(KEY_CURRENT_DATE, value) }

    override fun changeUserName(name: String) {
        sharedPreferences.edit { putString(KEY_USER_NAME, name) }
    }

    companion object {

        private const val FILE_NAME = "CosmosPreferences"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_CURRENT_DATE = "current_date"

        private var preferences: PhotoDayPreferences? = null

        fun newInstance(context: Context): PhotoDayPreferences {
            if (preferences == null) preferences = PhotoDayPreferences(context)
            return preferences!!
        }
    }
}