package com.newapptest.manuelperera.newapptest.domain.repository

import io.reactivex.Single

interface LoginRepository {

    fun login(clientId: String, clientSecret: String): Single<String>

}