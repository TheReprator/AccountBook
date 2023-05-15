package dev.reprator.common.datasource.remote

import dev.reprator.common.data.dataSource.SplashRemoteDataSource
import dev.reprator.common.datasource.remote.mapper.SplashMapper
import dev.reprator.common.datasource.remote.modal.SplashEntity
import dev.reprator.common.modal.SplashModal
import dev.reprator.common.util.AppError
import dev.reprator.common.util.AppResult
import dev.reprator.common.util.AppSuccess
import dev.reprator.common.util.safeApiCall
import dev.reprator.common.util.toResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

private const val BASE_URL = "http://192.168.0.186:8080"


class SplashRemoteDataSourceImpl(private val httpClient: HttpClient, private val mapper: SplashMapper): SplashRemoteDataSource {

    private suspend fun splashDataApi(): AppResult<SplashModal> {

        val dataRequest = httpClient.get("$BASE_URL/splash").toResult<SplashEntity>()

        val result = when (dataRequest) {
            is AppSuccess -> {
                AppSuccess(mapper.map(dataRequest.data))
            }

            is AppError -> {
                AppError(message = dataRequest.message, throwable = dataRequest.throwable)
            }
        }
        return result
    }

    override suspend fun splashRemoteDataSource(): Flow<AppResult<SplashModal>> {
        return flowOf(safeApiCall(call = { splashDataApi() }))
    }
}