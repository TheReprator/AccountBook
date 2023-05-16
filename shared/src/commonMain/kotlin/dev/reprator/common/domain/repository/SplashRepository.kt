package dev.reprator.common.domain.repository

import dev.reprator.common.modal.SplashModal
import dev.reprator.common.util.AppResult
import kotlinx.coroutines.flow.Flow

interface SplashRepository {
    suspend fun splashData(): Flow<AppResult<SplashModal>>
}