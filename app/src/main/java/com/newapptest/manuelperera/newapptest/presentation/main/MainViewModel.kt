package com.newapptest.manuelperera.newapptest.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.newapptest.manuelperera.newapptest.data.model.base.Results
import com.newapptest.manuelperera.newapptest.domain.usecase.login.LoginUseCase
import com.newapptest.manuelperera.newapptest.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private var _ldLogin = MutableLiveData<Unit>()
    private var parentJob = Job()
    private val jobs = setOf(LoginUseCase.job)
    val ldLogin: LiveData<Unit>
        get() = _ldLogin

    fun login() {
        loading(true)
        launch(LoginUseCase.job.second) {
            val data: Results = loginUseCase(LoginUseCase.Params())
            if (data is Results.Success<*>) {
                _ldLogin.postValue(Unit)
            } else {
                handleFailure((data as Results.Error).exception)
            }
            withContext(Dispatchers.Main) {
                loading(false)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = parentJob

    override fun onCleared() {
        super.onCleared()
        jobs.forEach {
            it.second.cancel()
        }
    }
}