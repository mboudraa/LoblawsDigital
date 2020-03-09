package com.mbo.loblaws

import android.app.Application
import android.content.Context
import timber.log.Timber


class App : Application() {

    val injector by lazy {
        DaggerAppComponent.builder()
            .setContext(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }
}

val Context.app: App
    get() = App.get(this)

val Context.injector: AppComponent
    get() = app.injector
