package com.newapptest.manuelperera.newapptest.presentation.base

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.domain.model.base.Failure
import dagger.Lazy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    @Inject
    lateinit var resources: Lazy<Resources>

    private var _ldFailure: MutableLiveData<Failure> = MutableLiveData()
    val ldFailure: LiveData<Failure>
        get() = _ldFailure
    private var _ldLoading: MutableLiveData<Boolean> = MutableLiveData()
    val ldLoading: LiveData<Boolean>
        get() = _ldLoading

    private val compositeDisposable = CompositeDisposable()

    protected fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun handleFailure(throwable: Failure) {
        val failure = throwable as? Failure ?: Failure.Error(
                throwable.message ?: resources.get().getString(R.string.unknown_error)
        )
        _ldFailure.value = failure
    }

    protected fun loading(visible: Boolean) {
        _ldLoading.value = visible
    }

}