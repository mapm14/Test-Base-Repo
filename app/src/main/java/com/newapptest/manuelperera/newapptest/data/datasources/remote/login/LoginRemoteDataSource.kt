package com.newapptest.manuelperera.newapptest.data.datasources.remote.login

import com.newapptest.manuelperera.newapptest.data.datasources.remote.base.BaseRemoteDataSource
import com.newapptest.manuelperera.newapptest.data.model.base.Results
import com.newapptest.manuelperera.newapptest.data.net.retrofitapi.login.LoginApi
import io.reactivex.Single
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    private val loginApi: LoginApi
) : BaseRemoteDataSource() {

    fun login(clientId: String, clientSecret: String): Single<String> =
        modifySingle(loginApi.login(clientId, clientSecret))

    suspend fun loginCr(clientId: String, clientSecret: String): Results {
        val loginResponse = loginApi.loginCr(clientId, clientSecret)
        return modifyDef(loginResponse).data
    }
}