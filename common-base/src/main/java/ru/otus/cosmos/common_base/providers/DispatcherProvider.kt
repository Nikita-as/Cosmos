package ru.otus.cosmos.common_base.providers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun io(): CoroutineDispatcher
    fun computation(): CoroutineDispatcher
    fun mainThread(): CoroutineDispatcher
}