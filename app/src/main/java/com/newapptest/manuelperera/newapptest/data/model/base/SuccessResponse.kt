package com.newapptest.manuelperera.newapptest.data.model.base

import com.newapptest.manuelperera.newapptest.domain.model.base.ResponseObject
import com.newapptest.manuelperera.newapptest.domain.model.base.Success

class SuccessResponse(val code: Int) : ResponseObject<Success> {

    override fun toAppDomain() = Success(code)

}