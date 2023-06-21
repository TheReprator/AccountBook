package dev.reprator.khatabook.ios.compose

import dev.reprator.khatabook.expect.appInitKoin
import dev.reprator.khatabook.util.NetworkDetector
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

fun starKoinApp() {
    appInitKoin()
}

class AppInitializer : KoinComponent {

    private val networkDetector : NetworkDetector by inject()

    fun starOperation() {
        networkDetector.startMonitor()
    }


    fun stopOperation(){

    }
}
