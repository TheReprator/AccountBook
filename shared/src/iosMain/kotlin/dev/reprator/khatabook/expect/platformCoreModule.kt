package dev.reprator.khatabook.expect

import dev.reprator.khatabook.impl.NetworkDetectorImpl
import dev.reprator.khatabook.util.NetworkDetector
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal actual val platformCoreModule: Module = module {
    singleOf(::NetworkDetectorImpl) bind NetworkDetector::class
}