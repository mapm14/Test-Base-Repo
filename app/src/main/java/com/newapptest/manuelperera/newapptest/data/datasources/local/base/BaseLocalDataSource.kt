package com.newapptest.manuelperera.newapptest.data.datasources.local.base

import android.content.SharedPreferences
import com.newapptest.manuelperera.newapptest.domain.model.base.Failure
import dagger.Lazy
import io.reactivex.Single
import javax.inject.Inject

open class BaseLocalDataSource {

    @Inject
    lateinit var sharedPreferences: Lazy<SharedPreferences>

    protected fun saveStringInPreferences(key: String, value: String): Single<String> {
        return Single.create<String> {
            try {
                sharedPreferences.get().edit().putString(key, value).apply()
                it.onSuccess(value)
            } catch (e: Exception) {
                it.onError(Failure.Error(e.localizedMessage))
            }
        }
    }

    protected fun getStringFromPreferences(key: String): String {
        return sharedPreferences.get().getString(key, "") ?: ""
    }

}