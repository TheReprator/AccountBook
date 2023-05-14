package dev.reprator.common.data.dataSourceImpl

import dev.reprator.common.SplashModal
import dev.reprator.common.data.dataSource.SplashRemoteDataSource
import dev.reprator.common.domain.repository.SplashRepository
import dev.reprator.common.util.AppResult
import kotlinx.coroutines.flow.Flow

class SplashRemoteDataSourceImpl(private val repository: SplashRemoteDataSource): SplashRepository {

    override suspend fun splashData(): Flow<AppResult<SplashModal>> {
        //check for internet connection
        return repository.splashRemoteDataSource()
    }
}