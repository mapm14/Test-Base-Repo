package com.newapptest.manuelperera.newapptest.domain.usecase.login

import com.newapptest.manuelperera.newapptest.BuildConfig.CLIENT_ID
import com.newapptest.manuelperera.newapptest.BuildConfig.CLIENT_SECRET
import com.newapptest.manuelperera.newapptest.data.model.base.Results
import com.newapptest.manuelperera.newapptest.domain.repository.LoginRepository
import com.newapptest.manuelperera.newapptest.domain.usecase.base.UseCase
import com.newapptest.manuelperera.newapptest.domain.usecase.login.LoginUseCase.Params
import kotlinx.coroutines.Job
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginApiRepository: LoginRepository
) : UseCase<String, Params> {


    override suspend fun invoke(params: Params): Results {
        return loginApiRepository.login(params.clientId, params.clientSecret)
    }


    class Params(
        val clientId: String = CLIENT_ID,
        val clientSecret: String = CLIENT_SECRET
    )

    companion object {
        val job = Pair("login_job", Job())
    }
}