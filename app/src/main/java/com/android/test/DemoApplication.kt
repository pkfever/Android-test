package com.android.test

import android.app.Application
import com.android.test.di.AppModule
import com.android.test.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DemoApplication)
            modules(listOf(NetworkModule, AppModule))
        }
    }
}