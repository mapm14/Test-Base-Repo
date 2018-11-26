package com.newapptest.manuelperera.newapptest.data.net.retrofitapi.login

import com.newapptest.manuelperera.newapptest.data.model.login.LoginResponse
import com.newapptest.manuelperera.newapptest.data.net.base.Constants.LOGIN_END_POINT
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST(LOGIN_END_POINT)
    fun login(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String = "client_credentials"
    ): Single<Result<LoginResponse>>

}