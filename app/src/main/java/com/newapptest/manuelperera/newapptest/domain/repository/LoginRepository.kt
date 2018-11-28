package com.newapptest.manuelperera.newapptest.domain.repository

import com.newapptest.manuelperera.newapptest.data.model.base.Results

interface LoginRepository {

    suspend fun login(clientId: String, clientSecret: String): Results

}