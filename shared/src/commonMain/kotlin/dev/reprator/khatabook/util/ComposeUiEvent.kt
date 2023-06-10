package dev.reprator.khatabook.util

sealed interface ComposeUiEvent {
    object ErrorLoad: ComposeUiEvent
    object Refresh: ComposeUiEvent
}