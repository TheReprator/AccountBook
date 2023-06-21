package dev.reprator.khatabook.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import dev.reprator.khatabook.screens.home.HomeScreen
import dev.reprator.khatabook.util.NetworkDetector
import io.github.xxfast.decompose.LocalComponentContext
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

  private val networkDetector: NetworkDetector by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)
    val rootComponentContext: DefaultComponentContext = defaultComponentContext()

    networkDetector.startMonitor()

    setContent {
      CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        NyTimesTheme {
          Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
          ) {
            HomeScreen()
          }
        }
      }
    }
  }
}
