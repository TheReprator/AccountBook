package dev.reprator.khatabook.datasource.remote

import dev.reprator.khatabook.data.dataSource.SplashRemoteDataSource
import dev.reprator.khatabook.datasource.remote.mapper.SplashMapper
import dev.reprator.khatabook.modal.SplashModal
import dev.reprator.khatabook.util.AppError
import dev.reprator.khatabook.util.AppResult
import dev.reprator.khatabook.util.AppSuccess
import dev.reprator.khatabook.util.safeApiCall

class SplashDataSourceRemoteImpl(private val apiService: KhataBookApiService,
                                 private val mapper: SplashMapper): SplashRemoteDataSource {

    private suspend fun splashDataApi(): AppResult<SplashModal> {

        return when (val dataRequest = apiService.splashApi()) {
            is AppSuccess -> {
                AppSuccess(mapper.map(dataRequest.data.data!!))
            }

            is AppError -> {
                AppError(message = dataRequest.message ?: dataRequest.throwable?.cause?.message, throwable = dataRequest.throwable)
            }
        }
    }

    override suspend fun splashRemoteDataSource(): AppResult<SplashModal> {
        return safeApiCall(call = { splashDataApi() })
    }
}