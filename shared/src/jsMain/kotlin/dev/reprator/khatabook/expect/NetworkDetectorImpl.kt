package dev.reprator.khatabook.expect

import dev.reprator.khatabook.util.NetworkDetector
import kotlinx.browser.window

actual class NetworkDetectorImpl actual constructor(private val context: Context): NetworkDetector {

    actual override val isConnected: Boolean
        get() = window.navigator.onLine

    actual override fun startMonitor() {

        window.addEventListener("online", {

        })

        window.addEventListener("offline", {

        })

    }

    actual override fun stopMonitor() {

    }
}
