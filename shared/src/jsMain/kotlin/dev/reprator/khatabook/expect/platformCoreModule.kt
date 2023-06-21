package dev.reprator.khatabook.expect

import dev.reprator.khatabook.impl.NetworkDetectorImpl
import dev.reprator.khatabook.util.NetworkDetector
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

internal actual val platformCoreModule: Module = module {
    single<Boolean>(named(IS_DEBUG_MODE)) { true } //check for debug mode, not now
    singleOf(::NetworkDetectorImpl) bind NetworkDetector::class
    single<CoroutineContext>(named(VIEWMODEL_DEFAULT_DISPATCHER)) { Dispatchers.Unconfined  }
}