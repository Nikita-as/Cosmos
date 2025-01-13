package ru.otus.cosmos.common_base.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private var latestJob: suspend () -> Unit = {}

fun CoroutineScope.launchEvent(
    loadingFlow: MutableStateFlow<Boolean>? = null,
    block: suspend () -> Unit,
) {
    launch {
        loadingFlow?.value = true
        latestJob = block.also { block ->
            block.invoke()
        }
    }.invokeOnCompletion {
        loadingFlow?.value = false
    }
}