/*
 * Copyright 2021
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("DEPRECATION")

package dev.reprator.khatabook.connectivity

import android.content.Context
import dev.reprator.khatabook.connectivity.base.ConnectivityProvider
import dev.reprator.khatabook.util.NetworkDetector
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkDetectorImpl constructor(
    private val context: Context, override var isConnected: MutableStateFlow<Boolean> = MutableStateFlow(false),
) : NetworkDetector, ConnectivityProvider.ConnectivityStateListener {

    private var isSubscriptionAlreadyAdded = false

    private val provider: ConnectivityProvider by lazy { ConnectivityProvider.createProvider(context) }

    override fun onStateChange(state: ConnectivityProvider.NetworkState) {
        isConnected.value = state.hasInternet()
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
}
