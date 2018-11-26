package com.newapptest.manuelperera.newapptest.data.datasources.remote.login

import com.newapptest.manuelperera.newapptest.data.datasources.remote.base.BaseRemoteDataSource
import com.newapptest.manuelperera.newapptest.data.net.retrofitapi.login.LoginApi
import io.reactivex.Single
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    private val loginApi: LoginApi
) : BaseRemoteDataSource() {

    fun login(clientId: String, clientSecret: String): Single<String> =
        modifySingle(loginApi.login(clientId, clientSecret))

}