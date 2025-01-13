package ru.otus.cosmos.common_test.mock

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.otus.cosmos.common_base.providers.DispatcherProvider

class DispatcherProviderMock : DispatcherProvider {
    override fun io(): CoroutineDispatcher = Dispatchers.Unconfined

    override fun computation(): CoroutineDispatcher = Dispatchers.Unconfined

    override fun mainThread(): CoroutineDispatcher = Dispatchers.Unconfined
}