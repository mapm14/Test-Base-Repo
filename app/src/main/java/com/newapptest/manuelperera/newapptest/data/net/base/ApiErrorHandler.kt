package com.newapptest.manuelperera.newapptest.data.net.base

import android.content.res.Resources
import com.google.gson.Gson
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.data.model.base.ErrorResponse
import com.newapptest.manuelperera.newapptest.domain.model.base.ApiCodes
import com.newapptest.manuelperera.newapptest.domain.model.base.Failure
import dagger.Lazy
import okhttp3.ResponseBody
import java.io.IOException

object ApiErrorHandler {

    fun getExceptionType(response: retrofit2.Response<*>, resources: Lazy<Resources>): Failure {
        val code = response.code()
        return when {
            response.isSuccessful.not() -> getFailureErrorWithErrorResponse(
                response.errorBody(),
                resources
            )
            code == ApiCodes.UNAUTHOURIZED_REQUEST_CODE -> Failure.Unauthorized()
            code == ApiCodes.NO_MORE_DATA_CODE -> Failure.NoMoreData()
            else -> getFailureUnknownError(resources)
        }
    }

    fun getExceptionType(response: retrofit2.Response<*>? = null, e: Exception, resources: Lazy<Resources>): Failure {
        return when {
            response != null -> getExceptionType(response, resources)
            e is IOException -> Failure.Timeout(resources.get().getString(R.string.timeout_message))
            else -> getFailureUnknownError(resources)
        }
    }

    private fun getFailureUnknownError(resources: Lazy<Resources>): Failure.Error =
        Failure.Error(resources.get().getString(R.string.unknown_error))

    private fun getFailureErrorWithErrorResponse(errorBody: ResponseBody?, resources: Lazy<Resources>): Failure.Error {
        val errorInfo = parseErrorResponse(errorBody, resources)
        return Failure.Error(errorInfo.code, errorInfo.message)
    }

    private fun parseErrorResponse(responseBody: ResponseBody?, resources: Lazy<Resources>): ErrorResponse =
        responseBody?.let { Gson().fromJson(it.string(), ErrorResponse::class.java) }
            ?: ErrorResponse(ApiCodes.UNKNOWN_ERROR_CODE, resources.get().getString(R.string.unknown_error))


}