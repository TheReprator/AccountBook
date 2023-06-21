package dev.reprator.khatabook.impl

import dev.reprator.khatabook.util.NetworkDetector
import dev.reprator.khatabook.util.logger.AppLogger
import java.net.InetAddress

class NetworkDetectorImpl constructor(private val logger: AppLogger) :
    NetworkDetector {
     override val isConnected: Boolean
        get() = "127.0.0.1" != InetAddress.getLocalHost().hostAddress.toString()

     override fun startMonitor() {
    }

     override fun stopMonitor() {
    }

}