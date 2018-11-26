package com.newapptest.manuelperera.newapptest.data.net.base

import com.google.gson.Gson
import com.newapptest.manuelperera.newapptest.BuildConfig
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class BaseRetrofit @Inject constructor(
    baseHttpClient: BaseHttpClient,
    gson: Gson
) {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(baseHttpClient.okHttpClient)
        .build()

}