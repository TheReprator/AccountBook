package dev.reprator.common.datasource.remote

import dev.reprator.common.data.dataSource.SplashRemoteDataSource
import dev.reprator.common.datasource.remote.mapper.SplashMapper
import dev.reprator.common.datasource.remote.modal.DataResponseContainer
import dev.reprator.common.datasource.remote.modal.SplashEntity
import dev.reprator.common.modal.SplashModal
import dev.reprator.common.util.AppError
import dev.reprator.common.util.AppResult
import dev.reprator.common.util.AppSuccess
import dev.reprator.common.util.safeApiCall
import dev.reprator.common.util.toResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class SplashDataSourceRemoteImpl(private val httpClient: HttpClient, private val mapper: SplashMapper): SplashRemoteDataSource {

    val coroutineScope: CoroutineScope = MainScope()

    fun start() {
        coroutineScope.launch {
            splashRemoteDataSource().catch {
                println("${it.message} vikram")
            }.collect {
                println("${it} vikram result")
            }
        }
    }

    private suspend fun splashDataApi(): AppResult<SplashModal> {

        val dataRequest = httpClient.get("splash").toResult<DataResponseContainer<SplashEntity>>()

        val result = when (dataRequest) {
            is AppSuccess -> {
                AppSuccess(mapper.map(dataRequest.data.data!!))
            }

            is AppError -> {
                AppError(message = dataRequest.message ?: dataRequest.throwable?.cause?.message, throwable = dataRequest.throwable)
            }
        }
        return result
    }

    override suspend fun splashRemoteDataSource(): Flow<AppResult<SplashModal>> {
        return flowOf(safeApiCall(call = { splashDataApi() }))
    }
}