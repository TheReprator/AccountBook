package dev.reprator.khatabook.screens.splash

import androidx.compose.runtime.Composable
import dev.reprator.khatabook.domain.usecase.SplashUseCase
import dev.reprator.khatabook.util.ComposeUiEvent
import dev.reprator.khatabook.util.NetworkDetector
import dev.reprator.khatabook.util.abstract.MoleculeViewModel
import io.github.xxfast.decompose.router.SavedStateHandle
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.koin.dsl.koinApplication

class SplashViewModel(savedState: SavedStateHandle) : MoleculeViewModel<ComposeUiEvent, SplashUiModel>(), KoinComponent {

    private val useCase: SplashUseCase by inject()

    private val networkDetector: NetworkDetector by inject {
        parametersOf(this)
    }
    
    @Composable
    override fun models(events: Flow<ComposeUiEvent>): SplashUiModel {
        return SplashPresenter(events, useCase)
    }

}