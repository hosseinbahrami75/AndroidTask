package com.keyvan.android.utils

import android.app.Application
import com.keyvan.android.di.NetModule.netModule
import com.keyvan.android.di.RepositoryModule.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(netModule)
            modules(repositoryModule)
        }
    }
}