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

class KhataBookApiService(private val client: HttpClient) {

  suspend fun splashApi() = client.get("splash").toResult<DataResponseContainer<SplashEntity>>()
}