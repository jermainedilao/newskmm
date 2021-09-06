package com.jermaine.newskmm.network.base

import com.jermaine.newskmm.domain.core.applicationDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal open class BaseRemoteSource {
    fun launch(
        onError: suspend CoroutineScope.(Exception) -> Unit = {},
        action: suspend CoroutineScope.() -> Unit
    ) {
        GlobalScope.launch(applicationDispatcher) {
            try {
                action(this)
            } catch (e: Exception) {
                print(e)
                onError(this, e)
            }
        }
    }
}
