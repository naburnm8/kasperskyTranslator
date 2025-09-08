package ru.bmstu.naburnm8.translator

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import ru.bmstu.naburnm8.translator.di.dataModule
import ru.bmstu.naburnm8.translator.di.networkModule
import ru.bmstu.naburnm8.translator.di.viewModelModule

class TranslatorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TranslatorApplication)
            modules(listOf(viewModelModule, networkModule, dataModule))
        }
    }
}