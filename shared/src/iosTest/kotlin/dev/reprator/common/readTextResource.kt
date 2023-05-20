package dev.reprator.common

import platform.Foundation.NSBundle

actual fun readTextResource(resourceName: String): String {
    val file =
        NSBundle.mainBundle.pathForResource("/src/commonTest/resources/$resourceName", "json")
    return file ?: ""
}
