package com.newapptest.manuelperera.newapptest.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newapptest.manuelperera.newapptest.domain.model.base.Failure
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private var _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure>
        get() = _failure

    private val compositeDisposable = CompositeDisposable()

    protected fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun handleFailure(failure: Failure) {
        this._failure.value = failure
    }

}