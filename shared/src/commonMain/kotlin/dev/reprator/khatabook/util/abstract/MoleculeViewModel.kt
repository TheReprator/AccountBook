package dev.reprator.khatabook.util.abstract

import androidx.compose.runtime.Composable
import app.cash.molecule.RecompositionClock
import app.cash.molecule.launchMolecule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class MoleculeViewModel<Event, Model> : ViewModel() {

    // Events have a capacity large enough to handle simultaneous UI events, but
    // small enough to surface issues if they get backed up for some reason.
    private val events = MutableSharedFlow<Event>(extraBufferCapacity = 20)

    val models: StateFlow<Model> by lazy(LazyThreadSafetyMode.NONE) {
        launchMolecule(clock = RecompositionClock.Immediate) {
            models(events)
        }
    }

    fun take(event: Event) {
        if (!events.tryEmit(event)) {
            error("Event buffer overflow.")
        }
    }

    @Composable
    protected abstract fun models(events: Flow<Event>): Model
}