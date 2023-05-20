package dev.reprator.common.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.MainCoroutineDispatcher

internal class AndroidAppCoroutineDispatchers constructor() : AppCoroutineDispatchers {
  override val main: CoroutineDispatcher get() = Dispatchers.Main
  override val io: CoroutineDispatcher get() = Dispatchers.IO
  override val default: CoroutineDispatcher get() = Dispatchers.Default
  override val unconfined: CoroutineDispatcher get() = Dispatchers.Unconfined
  override val immediateMain: MainCoroutineDispatcher get() = Dispatchers.Main.immediate
}
