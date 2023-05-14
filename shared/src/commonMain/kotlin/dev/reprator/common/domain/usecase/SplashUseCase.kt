package dev.reprator.common.domain.usecase

import dev.reprator.common.domain.repository.SplashRepository

class SplashUseCase(private val repository: SplashRepository) {

    suspend operator fun invoke() {
        repository.splashData()
    }
}