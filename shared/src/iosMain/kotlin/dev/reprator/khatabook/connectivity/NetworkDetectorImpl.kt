package dev.reprator.khatabook.connectivity

import dev.reprator.khatabook.util.NetworkDetector
import kotlinx.coroutines.flow.MutableStateFlow
import platform.Network.nw_path_get_status
import platform.Network.nw_path_monitor_cancel
import platform.Network.nw_path_monitor_create
import platform.Network.nw_path_monitor_set_queue
import platform.Network.nw_path_monitor_set_update_handler
import platform.Network.nw_path_monitor_start
import platform.Network.nw_path_monitor_t
import platform.Network.nw_path_status_satisfied
import platform.Network.nw_path_status_t
import platform.darwin.DISPATCH_QUEUE_PRIORITY_DEFAULT
import platform.darwin.dispatch_queue_attr_make_with_qos_class
import platform.darwin.dispatch_queue_attr_t
import platform.darwin.dispatch_queue_create
import platform.darwin.dispatch_queue_serial_t
import platform.darwin.dispatch_queue_t
import platform.posix.QOS_CLASS_UTILITY

class NetworkDetectorImpl : NetworkDetector {

    override var isConnected: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private var nwMonitor: nw_path_monitor_t = null
    private var monitorQueue: dispatch_queue_t = null

    override fun startMonitor() {
        /*val attrs: dispatch_queue_attr_t = dispatch_queue_attr_make_with_qos_class(
            dispatch_queue_serial_t.new(), QOS_CLASS_UTILITY, DISPATCH_QUEUE_PRIORITY_DEFAULT)
        monitorQueue= dispatch_queue_create("com.example.network.monitor", attrs)*/
        monitorQueue = dispatch_queue_create("com.example.network.monitor", null)

        nwMonitor = nw_path_monitor_create()
        nw_path_monitor_set_queue(nwMonitor, monitorQueue)

        nw_path_monitor_set_update_handler(nwMonitor) { path ->
            println("vikramAppTest path: ${path}")
            val status: nw_path_status_t = nw_path_get_status(path)
            println("vikramAppTest nw_path_status_t: ${nw_path_status_t}")
            isConnected.value = nw_path_status_satisfied == status
            println("vikramAppTest netStatus: ${isConnected.value}")
        }

        nw_path_monitor_start(nwMonitor)
    }

    override fun stopMonitor() {
        nw_path_monitor_cancel(nwMonitor)
    }
}
