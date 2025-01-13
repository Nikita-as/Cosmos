package ru.otus.cosmos.common_base.providers

import android.content.Context
import androidx.annotation.StringRes

internal class StringProviderImpl(private val context: Context) : StringProvider {

    override fun getString(@StringRes resId: Int?): String {
        if (resId == null) return ""
        return context.getString(resId)
    }

    override fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }
}