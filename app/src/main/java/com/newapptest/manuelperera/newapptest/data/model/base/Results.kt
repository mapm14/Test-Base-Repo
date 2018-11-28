package com.newapptest.manuelperera.newapptest.data.model.base

import com.newapptest.manuelperera.newapptest.domain.model.base.Failure

sealed class Results {

    class Success<T>(val success: T) : Results()

    class Error(val exception: Failure) : Results()
}


inline class ResResp(val data: Results)