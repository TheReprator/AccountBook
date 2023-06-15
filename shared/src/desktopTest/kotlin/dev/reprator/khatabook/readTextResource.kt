package dev.reprator.khatabook

import java.io.File

actual fun readTextResource(resourceName: String): String =
  File("/Users/vikramsingh/Desktop/code/kmp/Khatabook_KMP/shared/src/desktopTest/kotlin/dev/reprator/khatabook/$resourceName").readText()
