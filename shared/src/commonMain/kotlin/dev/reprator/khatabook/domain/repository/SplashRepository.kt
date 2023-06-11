package dev.reprator.khatabook.domain.repository

import dev.reprator.khatabook.modal.SplashModal
import dev.reprator.khatabook.util.AppResult

interface SplashRepository {
    suspend fun splashData(): AppResult<SplashModal>
}