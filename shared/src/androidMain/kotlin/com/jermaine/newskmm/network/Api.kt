package com.jermaine.newskmm.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*


internal actual object Api {
    actual val httpClient by lazy {
        HttpClient(CIO) {
            install(ResponseObserver) {
                onResponse { response ->
                    println("HTTP status: ${response.status.value}")
                }
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    useDefaultTransformers = true
                })
            }
        }
    }
}