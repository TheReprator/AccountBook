package dev.reprator.khatabook

import dev.reprator.common.MainView
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MainView()
    }
}