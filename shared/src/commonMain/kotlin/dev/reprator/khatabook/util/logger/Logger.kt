package dev.reprator.khatabook.util.logger

interface Logger {

    /** Log a verbose exception and a message with optional format args.  */
    fun v(throwable: Throwable? = null, message: () -> String = { "" })

    /** Log a verbose exception and a message with optional format args.  */
    fun d(throwable: Throwable? = null, message: () -> String = { "" })

    /** Log a verbose exception and a message with optional format args.  */
    fun i(throwable: Throwable? = null, message: () -> String = { "" })

    /** Log a verbose exception and a message with optional format args.  */
    fun e(throwable: Throwable? = null, message: () -> String = { "" })
}
