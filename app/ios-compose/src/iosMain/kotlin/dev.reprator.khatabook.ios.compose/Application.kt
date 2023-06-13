package dev.reprator.khatabook.ios.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import dev.reprator.khatabook.connectivity.NetworkDetectorImpl
import dev.reprator.khatabook.screens.home.HomeScreen
import io.github.xxfast.decompose.LocalComponentContext
import platform.UIKit.UIViewController

fun Main(): UIViewController = ComposeUIViewController {
  val lifecycle = LifecycleRegistry()
  val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)

  CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
    MaterialTheme {
      HomeScreen()
      NetworkDetectorImpl().startMonitor()
    }
  }
}
