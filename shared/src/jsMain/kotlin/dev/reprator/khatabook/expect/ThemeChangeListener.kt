package dev.reprator.khatabook.expect

import kotlinx.browser.window
import org.w3c.dom.MediaQueryList

class ThemeChangeListener constructor() {

    var theme: Theme = Theme.SYSTEM

    fun startMonitor() {

        window.matchMedia("(prefers-color-scheme: dark)").addEventListener("change", {
            val event = it.target as MediaQueryList
            theme = if (event.matches)
                Theme.DARK
            else
                Theme.LIGHT


        })


    }

    enum class Theme {
        LIGHT,
        DARK,
        SYSTEM,
    }
}
