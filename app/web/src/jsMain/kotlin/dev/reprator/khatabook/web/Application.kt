package dev.reprator.khatabook.web

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import dev.reprator.khatabook.expect.WebContext
import dev.reprator.khatabook.screens.home.HomeScreen
import dev.reprator.khatabook.web.utils.BrowserViewportWindow
import io.github.xxfast.decompose.LocalComponentContext
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady
import kotlin.browser.window

fun main() {
  onWasmReady {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)

    println("vikramThemeJS: ${getTheme()}")
    BrowserViewportWindow("Khatabook") {
      CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        MaterialTheme {
          HomeScreen(WebContext)
        }
      }
    }
  }
}

fun getTheme(): String {
  return if( window.matchMedia("(prefers-color-scheme:dark)").matches) {
    "dark";
  } else {
    "light";
  }
}