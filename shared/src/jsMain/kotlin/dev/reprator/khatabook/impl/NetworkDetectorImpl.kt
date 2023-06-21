package dev.reprator.khatabook.impl

import dev.reprator.khatabook.util.NetworkDetector
import kotlinx.browser.window

class NetworkDetectorImpl constructor(): NetworkDetector {

    override val isConnected: Boolean
        get() = window.navigator.onLine

    override fun startMonitor() {

        window.addEventListener("online", {

        })

        window.addEventListener("offline", {

        })

    }

    override fun stopMonitor() {

    }
}