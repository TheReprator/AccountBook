package dev.reprator.khatabook.datasource.remote

import dev.reprator.khatabook.datasource.remote.modal.DataResponseContainer
import dev.reprator.khatabook.datasource.remote.modal.SplashEntity
import dev.reprator.khatabook.util.toResult
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class KhataBookApiService(private val client: HttpClient = clientApp) {

  suspend fun splashApi() = client.get("splash").toResult<DataResponseContainer<SplashEntity>>()
}


@OptIn(ExperimentalSerializationApi::class)
val clientApp = HttpClient {

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
    header(HttpHeaders.AccessControlAllowOrigin, "*")
    header(HttpHeaders.ContentType, ContentType.Application.Json)
  }
}