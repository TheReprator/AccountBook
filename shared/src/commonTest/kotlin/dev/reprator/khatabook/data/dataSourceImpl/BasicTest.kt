package dev.reprator.khatabook.data.dataSourceImpl

import dev.reprator.khatabook.TestAppCoroutineDispatchers
import dev.reprator.khatabook.data.dataSource.SplashRemoteDataSource
import dev.reprator.khatabook.datasource.remote.SplashDataSourceRemoteImpl
import dev.reprator.khatabook.datasource.remote.mapper.SplashMapper
import dev.reprator.khatabook.readTextResource
import dev.reprator.khatabook.util.AppSuccess
import dev.reprator.khatabook.util.TestApiClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandler
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class BasicTest {

    private lateinit var httpClient: HttpClient
    private lateinit var handlerChannel: Channel<MockRequestHandler>
    private val testAppCoroutineDispatchers = TestAppCoroutineDispatchers()

    @BeforeTest
    fun setup() {
        handlerChannel = Channel(Channel.UNLIMITED)
        httpClient = TestApiClient.createHttpClient(MockEngine) {
            addHandler { request ->
                handlerChannel.receive()(request)
            }
        }
    }

    @AfterTest
    fun teardown() {
        httpClient.close()
        handlerChannel.close()
    }


    @Test
    fun actualTest() = runTest(testAppCoroutineDispatchers.testCoroutineDispatcher) {

        val splashRemote: SplashRemoteDataSource =
            SplashDataSourceRemoteImpl(httpClient, testAppCoroutineDispatchers, SplashMapper())
        val responseString = readTextResource("splash.json")

        handlerChannel.trySend { request ->
            check(request.method == HttpMethod.Get)

            respond(
                content = responseString,
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString()
                )
            )
        }


        val result = splashRemote.splashRemoteDataSource().first()
        assertTrue(result is AppSuccess)
        assertTrue(result.data.languageList.first().name =="vikram")
    }
}