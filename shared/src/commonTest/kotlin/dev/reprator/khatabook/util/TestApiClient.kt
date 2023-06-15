package dev.reprator.khatabook.util

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json


object TestApiClient {

    @OptIn(ExperimentalSerializationApi::class)
    fun <T : HttpClientEngineConfig> createHttpClient(
        engineFactory: HttpClientEngineFactory<T>,
        block: T.() -> Unit,
    ): HttpClient = HttpClient(engineFactory) {

        engine(block)

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

        install(Logging) { logger = Logger.SIMPLE }

        install(DefaultRequest) {
            url("http://192.168.0.186:8081/")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

}




/*

package dev.reprator.common.util

import io.ktor.client.*
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.suitableCharset
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.platformType
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

object TestApiClient {

    private lateinit var converter: KotlinxSerializationConverter

    fun <T : HttpClientEngineConfig> createHttpClient(
        engineFactory: HttpClientEngineFactory<T>,
        block: T.() -> Unit,
    ): HttpClient {
        val httpClient = HttpClient(engineFactory) {

            engine(block)

            install(HttpTimeout) {
                val timeout = 30000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }

            install(ContentNegotiation) {
                /*    json(Json {
                        explicitNulls = false
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    })
        */

                converter = KotlinxSerializationConverter(Json {
                    explicitNulls = false
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
                register(ContentType.Application.Json, converter)
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

        httpClient.responsePipeline.intercept(HttpResponsePipeline.Transform) { (info, body) ->
            if (body !is ByteReadChannel) return@intercept
            val response = context.response
            val apiResponse = if (response.status.isSuccess()) {
                val op = info.ofInnerClassParameter()
                println("vikramTest::: ${op}")
                    converter.deserialize(context.request.headers.suitableCharset(), op, body)
            } else {
                throw IllegalArgumentException("not a parsable error")
            }
            proceedWith(HttpResponseContainer(info, apiResponse!!))
        }

        return httpClient
    }

    private fun TypeInfo.ofInnerClassParameter(): TypeInfo {
        val typeProjection = kotlinType?.arguments?.get(0)
        val kType = typeProjection!!.type!!
        return TypeInfo(kType.classifier as KClass<*>, kType.platformType)
    }
}

* */