package dev.reprator.khatabook.impl

import dev.reprator.khatabook.util.NetworkDetector
import dev.reprator.khatabook.util.logger.AppLogger
import kotlinx.browser.window

class NetworkDetectorImpl constructor(private val logger: AppLogger): NetworkDetector {

    override val isConnected: Boolean
        get() = window.navigator.onLine

    override fun startMonitor() {

        window.addEventListener("online", {
            logger.e { "vikramAppTest path: online" }
        })

        window.addEventListener("offline", {
            logger.e { "vikramAppTest path: offline" }
        })

    }

    override fun stopMonitor() {

    }
}