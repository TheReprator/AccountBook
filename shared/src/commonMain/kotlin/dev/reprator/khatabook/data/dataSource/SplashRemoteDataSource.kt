package dev.reprator.khatabook.data.dataSource

import dev.reprator.khatabook.modal.SplashModal
import dev.reprator.khatabook.util.AppResult

interface SplashRemoteDataSource {
    suspend fun splashRemoteDataSource(): AppResult<SplashModal>
}