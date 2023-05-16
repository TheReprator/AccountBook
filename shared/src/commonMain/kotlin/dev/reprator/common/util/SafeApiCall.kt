package dev.reprator.common.util

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse

fun HttpResponse.toException() = ResponseException(this, toString())

suspend inline fun <reified T> HttpResponse.toResult(): AppResult<T> = try {
    val code = status.value
    val isSuccessFull = code in 200..299

    if (isSuccessFull) {
        AppSuccess(data = body())
    } else {
        AppError(toException())
    }
} catch (e: Exception) {
    AppError(e)
}


suspend fun <T : Any> safeApiCall(
    call: suspend () -> AppResult<T>,
    errorMessage: String? = null
): AppResult<T> {
    return try {
        call()
    } catch (e: Exception) {
        AppError(message = errorMessage ?: e.message)
    }
}