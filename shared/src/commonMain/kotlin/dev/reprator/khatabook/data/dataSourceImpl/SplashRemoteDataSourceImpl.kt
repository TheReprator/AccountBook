package dev.reprator.khatabook.data.dataSourceImpl

import dev.reprator.khatabook.data.dataSource.SplashRemoteDataSource
import dev.reprator.khatabook.domain.repository.SplashRepository
import dev.reprator.khatabook.modal.SplashModal
import dev.reprator.khatabook.util.AppError
import dev.reprator.khatabook.util.AppResult
import dev.reprator.khatabook.util.NetworkDetector

class SplashRemoteDataSourceImpl(private val repository: SplashRemoteDataSource,
    private val networkDetector: NetworkDetector
): SplashRepository {

    override suspend fun splashData(): AppResult<SplashModal> {
        return if(networkDetector.isConnected)
            repository.splashRemoteDataSource()
        else
            AppError(message="No interent")
    }
}