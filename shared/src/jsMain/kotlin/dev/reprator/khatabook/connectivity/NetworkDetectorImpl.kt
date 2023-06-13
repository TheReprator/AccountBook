package dev.reprator.khatabook.connectivity

import dev.reprator.khatabook.util.NetworkDetector
import kotlinx.browser.window
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkDetectorImpl : NetworkDetector {

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
