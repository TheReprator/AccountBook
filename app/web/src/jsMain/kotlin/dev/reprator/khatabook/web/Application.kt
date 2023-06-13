package dev.reprator.khatabook.web

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import dev.reprator.khatabook.connectivity.NetworkDetectorImpl
import dev.reprator.khatabook.screens.home.HomeScreen
import dev.reprator.khatabook.web.utils.BrowserViewportWindow
import io.github.xxfast.decompose.LocalComponentContext
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
  onWasmReady {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)

    BrowserViewportWindow("Khatabook") {
      CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        MaterialTheme {
          HomeScreen()
          NetworkDetectorImpl().startMonitor()
        }
      }
    }
  }
}
