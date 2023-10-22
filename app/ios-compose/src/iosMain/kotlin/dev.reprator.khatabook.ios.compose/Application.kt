package dev.reprator.khatabook.ios.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import dev.reprator.khatabook.screens.home.HomeScreen
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.RouterContext
import platform.UIKit.UIViewController

fun Main(): UIViewController = ComposeUIViewController {
  val lifecycle = LifecycleRegistry()
  val rootComponentContext = RouterContext(lifecycle = lifecycle)

  CompositionLocalProvider(LocalRouterContext provides rootComponentContext) {
    MaterialTheme {
      HomeScreen()
    }
  }
}
