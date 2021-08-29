package com.jermaine.newskmm.network

import io.ktor.client.*

internal expect object Api {
    val httpClient: HttpClient
}