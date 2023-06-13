package dev.reprator.khatabook.screens.splash

import androidx.compose.runtime.Composable
import dev.reprator.khatabook.data.dataSourceImpl.SplashRemoteDataSourceImpl
import dev.reprator.khatabook.datasource.remote.KhataBookApiService
import dev.reprator.khatabook.datasource.remote.SplashDataSourceRemoteImpl
import dev.reprator.khatabook.datasource.remote.mapper.SplashMapper
import dev.reprator.khatabook.domain.usecase.SplashUseCase
import dev.reprator.khatabook.util.ComposeUiEvent
import dev.reprator.khatabook.util.NetworkDetector
import dev.reprator.khatabook.util.abstract.MoleculeViewModel
import io.github.xxfast.decompose.router.SavedStateHandle
import kotlinx.coroutines.flow.Flow

class SplashViewModel(savedState: SavedStateHandle, private val networkDetector: NetworkDetector) : MoleculeViewModel<ComposeUiEvent, SplashUiModel>() {

    @Composable
    override fun models(events: Flow<ComposeUiEvent>): SplashUiModel {
        val useCase = SplashUseCase(SplashRemoteDataSourceImpl(SplashDataSourceRemoteImpl(
            KhataBookApiService(), SplashMapper()
        ), networkDetector))
        return SplashPresenter(events, useCase)
    }

}