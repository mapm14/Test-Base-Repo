package com.newapptest.manuelperera.newapptest.data.net.base

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.newapptest.manuelperera.newapptest.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class BaseRetrofit @Inject constructor(
    baseHttpClient: BaseHttpClient,
    gson: Gson
) {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(baseHttpClient.okHttpClient)
        .build()
}