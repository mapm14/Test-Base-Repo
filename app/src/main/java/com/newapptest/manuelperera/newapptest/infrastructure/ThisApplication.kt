package com.newapptest.manuelperera.newapptest.infrastructure

import com.newapptest.manuelperera.newapptest.infrastructure.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class ThisApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}