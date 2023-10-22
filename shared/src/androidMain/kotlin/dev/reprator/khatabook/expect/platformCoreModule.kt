package dev.reprator.khatabook.expect

import app.cash.molecule.AndroidUiDispatcher
import dev.reprator.khatabook.impl.NetworkDetectorImpl
import dev.reprator.khatabook.util.NetworkDetector
import io.github.aakira.napier.BuildConfig
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

internal actual val platformCoreModule: Module = module {
    single<Boolean>(named(IS_DEBUG_MODE)) { BuildConfig.DEBUG }

    single<CoroutineContext>(named(VIEWMODEL_DEFAULT_DISPATCHER)) { AndroidUiDispatcher.Main }

    single<NetworkDetector>{
        NetworkDetectorImpl(get())
    } bind NetworkDetector::class
}