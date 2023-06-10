package dev.reprator.khatabook.screens.splash

import dev.reprator.khatabook.modal.SplashModal
import dev.reprator.khatabook.util.ComposeUiModel

data class SplashUiModel(
    val data: SplashModal,
    override val isLoading: Boolean,
    override val error: String?,
    override val isEmpty: Boolean,
): ComposeUiModel