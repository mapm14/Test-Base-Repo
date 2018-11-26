package com.newapptest.manuelperera.newapptest.domain.model.base

import com.newapptest.manuelperera.newapptest.domain.model.base.ApiCodes.UNKNOWN_ERROR_CODE

sealed class Failure : Throwable() {

    class Error(val code: Int, val msg: String) : Failure() {
        constructor(message: String) : this(UNKNOWN_ERROR_CODE, message)
    }

    class Timeout(val msg: String) : Failure()

    class Unauthorized : Failure()

    abstract class FeatureFailure : Failure()

    class NoMoreData : FeatureFailure()

}