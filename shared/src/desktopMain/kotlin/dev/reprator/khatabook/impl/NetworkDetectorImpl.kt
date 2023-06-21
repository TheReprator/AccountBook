package dev.reprator.khatabook.impl

import dev.reprator.khatabook.util.NetworkDetector
import java.net.InetAddress

class NetworkDetectorImpl constructor() :
    NetworkDetector {
     override val isConnected: Boolean
        get() = "127.0.0.1" != InetAddress.getLocalHost().hostAddress.toString()

     override fun startMonitor() {
    }

     override fun stopMonitor() {
    }

}