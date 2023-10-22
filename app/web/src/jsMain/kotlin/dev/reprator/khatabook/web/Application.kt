package dev.reprator.khatabook.web

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import dev.reprator.khatabook.expect.appInitKoin
import dev.reprator.khatabook.screens.home.HomeScreen
import dev.reprator.khatabook.util.NetworkDetector
import dev.reprator.khatabook.web.utils.BrowserViewportWindow
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.RouterContext
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
  onWasmReady {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = RouterContext(lifecycle = lifecycle)

    startKoin()

    println("vikramThemeJS: ${getTheme()}")
    BrowserViewportWindow("AccountBook") {
      CompositionLocalProvider(LocalRouterContext provides rootComponentContext) {
        MaterialTheme {
          HomeScreen()
        }
      }
    }
  }
}

fun startKoin() {
  val koinApp = appInitKoin {

  }

  val networkDetector = koinApp.koin.get<NetworkDetector>()
  networkDetector.startMonitor()
}

fun getTheme(): String {
  return if( window.matchMedia("(prefers-color-scheme:dark)").matches) {
    "dark";
  } else {
    "light";
  }
}