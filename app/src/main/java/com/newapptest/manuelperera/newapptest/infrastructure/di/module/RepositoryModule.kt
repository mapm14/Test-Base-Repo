package com.newapptest.manuelperera.newapptest.infrastructure.di.module

import com.newapptest.manuelperera.newapptest.data.repository.implementation.login.LoginRepositoryImpl
import com.newapptest.manuelperera.newapptest.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun loginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

}