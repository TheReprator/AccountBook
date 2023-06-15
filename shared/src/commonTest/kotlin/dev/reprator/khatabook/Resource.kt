package dev.reprator.khatabook

// Common
const val RESOURCE_PATH = "./src/commonTest/resources"

expect class Resource(name: String) {
    val name: String

    fun exists(): Boolean
    fun readText(): String
}