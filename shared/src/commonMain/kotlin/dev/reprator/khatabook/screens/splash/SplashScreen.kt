package dev.reprator.khatabook.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.reprator.khatabook.expect.Context
import dev.reprator.khatabook.expect.NetworkDetectorImpl
import dev.reprator.khatabook.util.ComposeUiEvent
import io.github.xxfast.decompose.router.rememberOnRoute

@Composable
fun SplashScreen(context: Context) {

    val networkDetector = NetworkDetectorImpl(context)
    networkDetector.startMonitor()

    val viewModel: SplashViewModel =
        rememberOnRoute(SplashViewModel::class) { savedState -> SplashViewModel(savedState, networkDetector) }

    val model: SplashUiModel by viewModel.models.collectAsState()

    val onEvent: (ComposeUiEvent) -> Unit = { event ->
        viewModel.take(event)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(150.dp)
    ) {

        val inDark = isSystemInDarkTheme()
        println("vikramTheme: $inDark")
        AnimatedVisibility(visible = model.isLoading) {
            SplashLoader()
        }

        AnimatedVisibility(visible = model.error.isNullOrEmpty().not()) {
            SplashError(onEvent)
        }

        val isDataVisible = model.isLoading.not() && model.error.isNullOrEmpty()
        AnimatedVisibility(visible = isDataVisible) {
            SplashData()
        }
    }
}

@Composable
fun SplashData(modifier: Modifier = Modifier) {
    Text(text = "Data Loaded Successfully")
}


@Composable
fun SplashLoader() {
    CircularProgressIndicator()
}

@Composable
fun SplashError(onEvent: (ComposeUiEvent) -> Unit) {

    Button(
        content = {
            Text("An error occurred")
        },
        onClick = { onEvent(ComposeUiEvent.ErrorLoad) }
    )
}