package com.newapptest.manuelperera.newapptest.data.repository.implementation.login

import com.newapptest.manuelperera.newapptest.data.datasources.local.login.LoginLocalDataSource
import com.newapptest.manuelperera.newapptest.data.datasources.remote.login.LoginRemoteDataSource
import com.newapptest.manuelperera.newapptest.data.model.base.Results
import com.newapptest.manuelperera.newapptest.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginLocalDataSource: LoginLocalDataSource
) : LoginRepository {

    override suspend fun login(clientId: String, clientSecret: String): Results {
        return loginRemoteDataSource.loginCr(clientId, clientSecret).also {
            if (it is Results.Success<*>) {
                loginLocalDataSource.saveToken(it.success as String)
            }
        }
    }
}