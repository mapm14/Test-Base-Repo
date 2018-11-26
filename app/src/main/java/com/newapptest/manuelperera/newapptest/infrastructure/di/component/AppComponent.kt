package com.newapptest.manuelperera.newapptest.infrastructure.di.component

import android.app.Application
import com.newapptest.manuelperera.newapptest.domain.repository.LoginRepository
import com.newapptest.manuelperera.newapptest.infrastructure.ThisApplication
import com.newapptest.manuelperera.newapptest.infrastructure.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        NetModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<ThisApplication> {

    fun provideApp(app: ThisApplication)

    fun provideLoginRepository(): LoginRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}