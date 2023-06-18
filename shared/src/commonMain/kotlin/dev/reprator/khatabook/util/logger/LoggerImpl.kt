package dev.reprator.khatabook.util.logger

import io.github.aakira.napier.Napier

class TimberLogger : Logger {
    override fun v(throwable: Throwable?, message: () -> String) {
        Napier.v(throwable=throwable, message = message())
    }

    override fun d(throwable: Throwable?, message: () -> String) {
        Napier.d(throwable = throwable, message = message())
    }

    override fun i(throwable: Throwable?, message: () -> String) {
        Napier.i(throwable = throwable, message = message())
    }

    override fun e(throwable: Throwable?, message: () -> String) {
        Napier.e(throwable = throwable, message = message())
    }
}