package com.newapptest.manuelperera.newapptest.infrastructure.di.module

import com.newapptest.manuelperera.newapptest.data.net.base.BaseHttpClient
import com.newapptest.manuelperera.newapptest.data.net.base.BaseRetrofit
import com.newapptest.manuelperera.newapptest.data.net.retrofitapi.login.LoginApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(baseHttpClient: BaseHttpClient): OkHttpClient =
        baseHttpClient.okHttpClient

    @Provides
    @Singleton
    fun provideRetrofit(baseRetrofit: BaseRetrofit): Retrofit =
        baseRetrofit.retrofit

    @Provides
    fun provideLoginApi(retrofit: Retrofit): LoginApi =
        retrofit.create(LoginApi::class.java)

}