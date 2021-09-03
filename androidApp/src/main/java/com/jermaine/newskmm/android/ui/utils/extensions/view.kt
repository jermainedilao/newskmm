package com.jermaine.newskmm.android.ui.utils.extensions

import android.view.View
import com.jermaine.newskmm.android.ui.utils.NINJA_TAP_THROTTLE_TIME
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

/**
 * Reference: https://github.com/Kotlin/kotlinx.coroutines/issues/1446#issuecomment-625244176
 */
@ExperimentalCoroutinesApi
fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> {
    var job: Job = Job().apply { complete() }

    return onCompletion { job.cancel() }.run {
        flow {
            coroutineScope {
                collect { value ->
                    if (!job.isActive) {
                        emit(value)
                        job = launch { delay(windowDuration) }
                    }
                }
            }
        }
    }
}

@ExperimentalCoroutinesApi
fun View.ninjaTap(scope: CoroutineScope, click: () -> Unit) {
    val channel = Channel<Unit>(Channel.CONFLATED)
    val flow = channel.consumeAsFlow().throttleFirst(NINJA_TAP_THROTTLE_TIME)

    setOnClickListener {
        channel.trySend(Unit)
    }

    scope.launch(Dispatchers.Main) {
        flow.collect {
            click()
        }
    }
}
