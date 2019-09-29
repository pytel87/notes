package com.pytel.notes.ui.base

import android.app.Application
import com.pytel.notes.BuildConfig
import com.pytel.notes.framework.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class BaseApp : Application() {


    override fun onCreate(){
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(applicationContext)
            modules(listOf(apiServiceModule, appModule, useCaseModule, viewModelModule, databaseModule))
        }
    }
}