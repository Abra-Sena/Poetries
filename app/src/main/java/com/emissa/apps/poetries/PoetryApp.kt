package com.emissa.apps.poetries

import android.app.Application
import com.emissa.apps.poetries.di.applicationModule
import com.emissa.apps.poetries.di.networkModule
import com.emissa.apps.poetries.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PoetryApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PoetryApp)
            modules(listOf(networkModule, applicationModule, viewModelModule))
        }
    }
}