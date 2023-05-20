package dev.reprator.common.util

import io.ktor.client.*
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object TestApiClient {

    val client = HttpClient(MockEngine) {

        install(DataTransformationPlugin)

        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }

        install(ContentNegotiation) {
            json(Json {
                explicitNulls = false
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            class DebugKtorLogger : Logger {
                override fun log(message: String) {
                    println("Ktor vikram:: $message")
                }
            }
            logger = DebugKtorLogger()
            level = LogLevel.ALL
        }

        install(DefaultRequest) {
            url("http://192.168.0.186:8080/")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

}