package dev.reprator.khatabook.android

import android.app.Application
import dev.reprator.khatabook.expect.appInitKoin
import org.koin.android.ext.koin.androidContext

class AccountBook : Application() {

    override fun onCreate() {
        super.onCreate()

        appInitKoin {
            androidContext(this@AccountBook)
        }

    }
}
