package dev.reprator.khatabook.util

interface NetworkDetector {
    val isConnected: Boolean

    fun startMonitor()
    fun stopMonitor()
}