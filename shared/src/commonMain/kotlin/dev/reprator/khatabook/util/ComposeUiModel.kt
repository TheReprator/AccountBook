package dev.reprator.khatabook.util

interface ComposeUiModel{
    val isLoading: Boolean
    val error: String?
    val isEmpty: Boolean
}