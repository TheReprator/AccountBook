package dev.reprator.khatabook.domain.usecase

import dev.reprator.khatabook.domain.repository.SplashRepository
import dev.reprator.khatabook.modal.SplashModal
import dev.reprator.khatabook.util.AppResult

class SplashUseCase(private val repository: SplashRepository) {

    suspend operator fun invoke(): AppResult<SplashModal> {
        return repository.splashData()
    }
}