package com.newapptest.manuelperera.newapptest.data.model.login

import com.newapptest.manuelperera.newapptest.domain.model.base.ResponseObject

class LoginResponse(
    private val accessToken: String,
    private val tokenType: String,
    private val expiresIn: Int
) : ResponseObject<String> {

    override fun toAppDomain(): String = accessToken

}