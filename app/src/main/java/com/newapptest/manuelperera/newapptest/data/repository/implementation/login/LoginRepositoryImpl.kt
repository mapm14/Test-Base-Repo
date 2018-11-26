package com.newapptest.manuelperera.newapptest.data.repository.implementation.login

import com.newapptest.manuelperera.newapptest.data.datasources.local.login.LoginLocalDataSource
import com.newapptest.manuelperera.newapptest.data.datasources.remote.login.LoginRemoteDataSource
import com.newapptest.manuelperera.newapptest.domain.repository.LoginRepository
import io.reactivex.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginLocalDataSource: LoginLocalDataSource
) : LoginRepository {

    override fun login(clientId: String, clientSecret: String): Single<String> =
        loginRemoteDataSource.login(clientId, clientSecret)
            .flatMap { loginLocalDataSource.saveToken(it) }

}