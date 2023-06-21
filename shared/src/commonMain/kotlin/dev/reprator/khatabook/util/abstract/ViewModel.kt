package dev.reprator.khatabook.util.abstract

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import dev.reprator.khatabook.expect.VIEWMODEL_DEFAULT_DISPATCHER
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import kotlin.coroutines.CoroutineContext

open class ViewModel : InstanceKeeper.Instance, CoroutineScope, KoinComponent {
    private val dispatcherContext by inject<CoroutineContext>(named(VIEWMODEL_DEFAULT_DISPATCHER))

    override val coroutineContext: CoroutineContext = dispatcherContext + SupervisorJob()

    override fun onDestroy() {
        coroutineContext.cancel()
    }
}