package dev.reprator.common.util

interface NetworkDetector {
    val isConnected: Boolean

    fun startMonitor()
    fun stopMonitor()
}