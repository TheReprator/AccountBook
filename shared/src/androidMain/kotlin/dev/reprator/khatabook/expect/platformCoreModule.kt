package dev.reprator.khatabook.expect

import com.arkivanov.decompose.BuildConfig
import dev.reprator.khatabook.impl.NetworkDetectorImpl
import dev.reprator.khatabook.util.NetworkDetector
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

internal actual val platformCoreModule: Module = module {
    single<Boolean>(named(IS_DEBUG_MODE)) { BuildConfig.DEBUG}

    single<NetworkDetector>{
        NetworkDetectorImpl(get())
    } bind NetworkDetector::class
}