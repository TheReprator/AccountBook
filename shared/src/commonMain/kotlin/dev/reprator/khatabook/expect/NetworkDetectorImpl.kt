package dev.reprator.khatabook.expect

import dev.reprator.khatabook.util.NetworkDetector

expect class NetworkDetectorImpl(context: Context) : NetworkDetector {

     override val isConnected: Boolean

     override fun startMonitor()
     override fun stopMonitor()

 }