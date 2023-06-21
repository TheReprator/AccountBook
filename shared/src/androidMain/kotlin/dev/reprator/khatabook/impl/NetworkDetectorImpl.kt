package dev.reprator.khatabook.impl

import android.content.Context
import dev.reprator.khatabook.connectivity.base.ConnectivityProvider
import dev.reprator.khatabook.util.NetworkDetector

class NetworkDetectorImpl constructor(private val context: Context)
    : NetworkDetector, ConnectivityProvider.ConnectivityStateListener {

    private var networkStatus: Boolean = false

    private var isSubscriptionAlreadyAdded = false

    private val provider: ConnectivityProvider by lazy { ConnectivityProvider.createProvider(context) }

    override fun onStateChange(state: ConnectivityProvider.NetworkState) {
        networkStatus = state.hasInternet()
    }

    private fun ConnectivityProvider.NetworkState.hasInternet(): Boolean {
        return (this as? ConnectivityProvider.NetworkState.ConnectedState)?.hasInternet == true
    }

    override fun startMonitor() {
        if (isSubscriptionAlreadyAdded)
            return
        isSubscriptionAlreadyAdded = true
        provider.addListener(this)
    }

    override fun stopMonitor() {
        isSubscriptionAlreadyAdded = false
        provider.removeListener(this)
    }

    override val isConnected: Boolean
        get() = networkStatus
}