package dev.reprator.khatabook

import dev.reprator.common.MainView
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.reprator.common.start

fun main() = application {
    start()

    Window(onCloseRequest = ::exitApplication) {
        MainView()
    }
}