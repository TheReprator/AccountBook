package dev.reprator.common.data.dataSource

import dev.reprator.common.modal.SplashModal
import dev.reprator.common.util.AppResult
import kotlinx.coroutines.flow.Flow

interface SplashRemoteDataSource {
    suspend fun splashRemoteDataSource(): Flow<AppResult<SplashModal>>
}