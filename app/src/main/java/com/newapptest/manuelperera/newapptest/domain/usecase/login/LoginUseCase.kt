package com.newapptest.manuelperera.newapptest.domain.usecase.login

import com.newapptest.manuelperera.newapptest.BuildConfig.CLIENT_ID
import com.newapptest.manuelperera.newapptest.BuildConfig.CLIENT_SECRET
import com.newapptest.manuelperera.newapptest.domain.repository.LoginRepository
import com.newapptest.manuelperera.newapptest.domain.usecase.base.UseCase
import com.newapptest.manuelperera.newapptest.domain.usecase.login.LoginUseCase.Params
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginApiRepository: LoginRepository
) : UseCase<String, Params> {

    override fun invoke(params: Params): Single<String> = loginApiRepository
        .login(params.clientId, params.clientSecret)
        .observeOn(AndroidSchedulers.mainThread())

    class Params(
        val clientId: String = CLIENT_ID,
        val clientSecret: String = CLIENT_SECRET
    )

}