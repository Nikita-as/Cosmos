package ru.otus.cosmos.common_base.providers

import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes resId: Int?): String
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String
}