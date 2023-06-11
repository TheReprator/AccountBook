package dev.reprator.khatabook.data.dataSourceImpl

import dev.reprator.khatabook.data.dataSource.SplashRemoteDataSource
import dev.reprator.khatabook.domain.repository.SplashRepository
import dev.reprator.khatabook.modal.SplashModal
import dev.reprator.khatabook.util.AppResult

class SplashRemoteDataSourceImpl(private val repository: SplashRemoteDataSource): SplashRepository {

    override suspend fun splashData(): AppResult<SplashModal> {
        //check for internet connection
        return repository.splashRemoteDataSource()
    }
}