package dev.reprator.common.domain.repository

import dev.reprator.common.SplashModal
import dev.reprator.common.util.AppResult
import kotlinx.coroutines.flow.Flow

interface SplashRepository {
    suspend fun splashData(): Flow<AppResult<SplashModal>>
}