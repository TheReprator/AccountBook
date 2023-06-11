package dev.reprator.khatabook.util

import kotlinx.coroutines.flow.StateFlow

interface NetworkDetector {
    val isConnected: StateFlow<Boolean>

    fun startMonitor()
    fun stopMonitor()
}