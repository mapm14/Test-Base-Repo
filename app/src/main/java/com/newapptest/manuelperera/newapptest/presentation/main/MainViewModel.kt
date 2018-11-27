package com.newapptest.manuelperera.newapptest.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.newapptest.manuelperera.newapptest.domain.usecase.login.LoginUseCase
import com.newapptest.manuelperera.newapptest.presentation.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private var _loginSuccess = MutableLiveData<Unit>()
    val loginSuccess: LiveData<Unit>
        get() = _loginSuccess

    fun login() {
        addSubscription(
            loginUseCase(LoginUseCase.Params())
                .subscribe({ _loginSuccess.value = Unit }, ::handleFailure)
        )
    }

}