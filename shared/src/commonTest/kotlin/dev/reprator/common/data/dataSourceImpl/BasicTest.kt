package dev.reprator.common.data.dataSourceImpl

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class BasicTest {

    @Test
    fun apiTest() = runTest {
        HttpClient(MockEngine)
    }
}