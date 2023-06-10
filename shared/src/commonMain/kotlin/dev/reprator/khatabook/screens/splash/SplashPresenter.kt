package dev.reprator.khatabook.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.reprator.khatabook.domain.usecase.SplashUseCase
import dev.reprator.khatabook.modal.SplashModal
import dev.reprator.khatabook.util.AppError
import dev.reprator.khatabook.util.AppSuccess
import dev.reprator.khatabook.util.ComposeUiEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

@Composable
fun SplashPresenter(events: Flow<ComposeUiEvent>, splashUseCase: SplashUseCase): SplashUiModel {
    var splashModal: SplashModal by remember { mutableStateOf(SplashModal(emptyList(), emptyList())) }
    var isLoading: Boolean by remember { mutableStateOf(true) }
    var error: String? by remember { mutableStateOf(null) }

    //To handle the reload or initial load
    var reloadState: Int by remember { mutableStateOf(0) }

    // Grab the list of breeds and sets the current selection to the first in the list.
    // Errors are ignored in this sample.
    LaunchedEffect(reloadState) {

        when(val data = splashUseCase()) {
            is AppSuccess -> {
                delay(1000)
                isLoading = false
                error = null
                splashModal = data.data
            }
            is AppError -> {
                delay(1000)
                isLoading = false
                error = data.message
            }
        }
    }

    // Handle UI events.
    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is ComposeUiEvent.ErrorLoad -> {
                    isLoading = true
                    error = null
                    reloadState++
                }
                else -> {}
            }
        }
    }

    return SplashUiModel(
        data = splashModal,
        isLoading = isLoading,
        error = error,
        isEmpty = false
    )
}