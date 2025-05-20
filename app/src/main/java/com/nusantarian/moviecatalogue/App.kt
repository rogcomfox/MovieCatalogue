package com.nusantarian.moviecatalogue

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import com.nusantarian.moviecatalogue.di.appModule
import com.nusantarian.moviecatalogue.util.getAppLocale
import com.nusantarian.moviecatalogue.util.setAppLocale
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    companion object {
        lateinit var instance: Application
        lateinit var res: Resources
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        res = resources

        //koin DI
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(appModule)
        }
    }

    override fun attachBaseContext(base: Context) {
        val lang = base.getAppLocale()
        super.attachBaseContext(ContextWrapper(base.setAppLocale(lang)))
    }
}