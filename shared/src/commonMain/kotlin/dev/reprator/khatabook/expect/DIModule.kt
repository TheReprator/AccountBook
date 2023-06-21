package dev.reprator.khatabook.expect

import dev.reprator.khatabook.data.dataSource.SplashRemoteDataSource
import dev.reprator.khatabook.data.dataSourceImpl.SplashRemoteDataSourceImpl
import dev.reprator.khatabook.datasource.remote.KhataBookApiService
import dev.reprator.khatabook.datasource.remote.SplashDataSourceRemoteImpl
import dev.reprator.khatabook.datasource.remote.mapper.SplashMapper
import dev.reprator.khatabook.domain.repository.SplashRepository
import dev.reprator.khatabook.domain.usecase.SplashUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

internal expect val platformCoreModule : Module


//commonMain module
private val commonCoreModule = module {
    //Add common core module implementations here

    single<HttpClient> {
        HttpClient {

            install(HttpTimeout) {
                val timeout = 30000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }

            install(ContentNegotiation) {
                json(Json {
                    explicitNulls = false
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(Logging) { logger = Logger.SIMPLE }

            install(DefaultRequest) {
                url("http://192.168.0.186:8081/")
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    single {
        KhataBookApiService(get())
    }
}




//https://github.com/InsertKoinIO/koin/issues/1341
// writing get() is important
val splashModule : Module get() =  module {
    factory {
        SplashMapper()
    }
    factoryOf(::SplashDataSourceRemoteImpl) bind SplashRemoteDataSource::class

    factoryOf(::SplashRemoteDataSourceImpl) bind SplashRepository::class

    factory{
        SplashUseCase(get())
    }
}


val coreModule : Module get() =  module {
    includes(commonCoreModule + platformCoreModule + splashModule)
}

fun appInitKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(coreModule)
    }