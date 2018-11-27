package com.newapptest.manuelperera.newapptest.infrastructure.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.newapptest.manuelperera.newapptest.infrastructure.viewmodel.ViewModelFactory
import com.newapptest.manuelperera.newapptest.infrastructure.viewmodel.ViewModelKey
import com.newapptest.manuelperera.newapptest.presentation.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsHomeViewModel(homeViewModel: HomeViewModel): ViewModel

}