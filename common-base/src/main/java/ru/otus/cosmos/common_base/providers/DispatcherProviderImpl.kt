package ru.otus.cosmos.common_base.providers

import kotlinx.coroutines.Dispatchers

internal class DispatcherProviderImpl : DispatcherProvider {
    override fun io() = Dispatchers.IO
    override fun computation() = Dispatchers.Default
    override fun mainThread() = Dispatchers.Main
}