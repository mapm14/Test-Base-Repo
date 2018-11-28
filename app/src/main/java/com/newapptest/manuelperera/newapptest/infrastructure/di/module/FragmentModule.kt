package com.newapptest.manuelperera.newapptest.infrastructure.di.module

import com.newapptest.manuelperera.newapptest.infrastructure.di.scope.PerView
import com.newapptest.manuelperera.newapptest.presentation.home.HomeFragment
import com.newapptest.manuelperera.newapptest.presentation.orderdetail.OrderDetailActivity
import com.newapptest.manuelperera.newapptest.presentation.orders.OrdersFragment
import com.newapptest.manuelperera.newapptest.presentation.user.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @PerView
    @ContributesAndroidInjector
    abstract fun homeFragmentInjector(): HomeFragment

    @PerView
    @ContributesAndroidInjector
    abstract fun ordersFragmentInjector(): OrdersFragment

    @PerView
    @ContributesAndroidInjector
    abstract fun userFragmentInjector(): UserFragment

    @PerView
    @ContributesAndroidInjector
    abstract fun orderDetailFragmentInjector(): OrderDetailActivity

}