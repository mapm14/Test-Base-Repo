package com.newapptest.manuelperera.newapptest.infrastructure.di.module

import com.newapptest.manuelperera.newapptest.infrastructure.di.scope.PerView
import com.newapptest.manuelperera.newapptest.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @PerView
    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity

}