package dev.reprator.khatabook.expect

import dev.reprator.khatabook.util.NetworkDetector
import java.net.InetAddress

actual class NetworkDetectorImpl actual constructor(private val context: Context) : NetworkDetector {
    actual override val isConnected: Boolean
        get() = "127.0.0.1" != InetAddress.getLocalHost().hostAddress.toString()

    actual override fun startMonitor() {
    }

    actual override fun stopMonitor() {
    }

}