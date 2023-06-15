package dev.reprator.khatabook

private external fun require(module: String): dynamic
private val fs = require("fs")

actual fun readTextResource(resourceName: String): String {
    return ResourceTest(resourceName).readText()
}


 class ResourceTest (name: String) {
    private val path = "./src/commonTest/resources/$name"

     fun exists(): Boolean = fs.existsSync(path) as Boolean

     fun readText(): String = fs.readFileSync(path, "utf8") as String
}