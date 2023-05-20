package dev.reprator.common

import java.io.File

actual fun readTextResource(resourceName: String): String =
  File("./src/commonTest/resources/$resourceName").readText()
