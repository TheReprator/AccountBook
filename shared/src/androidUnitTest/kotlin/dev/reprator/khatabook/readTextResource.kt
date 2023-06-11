package dev.reprator.khatabook

import java.io.File

actual fun readTextResource(resourceName: String): String =
  File("./src/commonTest/resources/$resourceName").readText()
