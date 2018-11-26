package com.newapptest.manuelperera.newapptest.data.datasources.local.login

import com.newapptest.manuelperera.newapptest.data.datasources.local.base.BaseLocalDataSource
import com.newapptest.manuelperera.newapptest.data.datasources.local.base.SharedPreferencesKeys.TOKEN_KEY
import io.reactivex.Single
import javax.inject.Inject

class LoginLocalDataSource @Inject constructor() : BaseLocalDataSource() {

    fun saveToken(token: String): Single<String> = saveStringInPreferences(TOKEN_KEY, token)

    fun getToken(): String = getStringFromPreferences(TOKEN_KEY)

}