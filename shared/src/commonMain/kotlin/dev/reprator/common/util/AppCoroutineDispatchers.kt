package dev.reprator.common.util

import kotlinx.coroutines.CoroutineDispatcher

interface AppCoroutineDispatchers {

  val main: CoroutineDispatcher

  val io: CoroutineDispatcher

  val default: CoroutineDispatcher

  val unconfined: CoroutineDispatcher

  val immediateMain: CoroutineDispatcher
}
