package com.newapptest.manuelperera.newapptest.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.newapptest.manuelperera.newapptest.domain.usecase.login.LoginUseCase
import com.newapptest.manuelperera.newapptest.presentation.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private var _ldLogin = MutableLiveData<Unit>()
    val ldLogin: LiveData<Unit>
        get() = _ldLogin

    fun login() {
        addSubscription(loginUseCase(LoginUseCase.Params())
                .doOnSubscribe { loading(true) }
                .doAfterTerminate { loading(false) }
                .subscribe({ _ldLogin.value = Unit }, ::handleFailure))
    }

}