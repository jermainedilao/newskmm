package com.jermaine.newskmm.domain.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val applicationDispatcher: CoroutineDispatcher = Dispatchers.Main