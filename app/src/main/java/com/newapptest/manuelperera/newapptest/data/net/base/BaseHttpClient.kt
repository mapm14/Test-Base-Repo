package com.newapptest.manuelperera.newapptest.data.net.base

import com.newapptest.manuelperera.newapptest.BuildConfig.*
import com.newapptest.manuelperera.newapptest.data.datasources.local.login.LoginLocalDataSource
import com.newapptest.manuelperera.newapptest.data.model.base.Results
import com.newapptest.manuelperera.newapptest.data.net.base.EndPoints.LOGIN_END_POINT
import com.newapptest.manuelperera.newapptest.domain.model.base.ApiCodes.UNAUTHOURIZED_REQUEST_CODE
import com.newapptest.manuelperera.newapptest.infrastructure.di.component.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import javax.inject.Inject

class BaseHttpClient @Inject constructor(
    private val loginLocalDataSource: LoginLocalDataSource
) {

    val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = if (DEBUG) BODY else NONE
            }
        )
        .addInterceptor { chain ->
            val url = chain.request().url().url().toString()
            val requestBuilder = chain.request().newBuilder()
            if (url.contains(LOGIN_END_POINT, true).not() && loginLocalDataSource.getToken().isNotEmpty()) {
                requestBuilder.addHeader("Authorization", "Bearer ${loginLocalDataSource.getToken()}")
            }
            chain.proceed(requestBuilder.build())
        }
        .authenticator { chain, response ->
            val url = chain.address().url().url().toString()
            val requestBuilder = response.request().newBuilder()

            if (url.contains(LOGIN_END_POINT, true).not() && response.code() == UNAUTHOURIZED_REQUEST_CODE) {
                val loginRepository = DaggerAppComponent.builder().build().provideLoginRepository()

                try {
                    CoroutineScope(Job()).launch {
                        val token = loginRepository.login(CLIENT_ID, CLIENT_SECRET)
                        when (token) {
                            is Results.Success<*> -> loginLocalDataSource.saveToken(token.success as String)
                            else -> throw Exception()
                        }
                        requestBuilder.addHeader("Authorization", "Bearer $token")
                    }
                } catch (e: Exception) {
                    loginLocalDataSource.saveToken("")
                    return@authenticator null
                }
            }

            requestBuilder.build()
        }.build()

}