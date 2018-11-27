package com.newapptest.manuelperera.newapptest.data.datasources.remote.base

import android.content.res.Resources
import com.google.gson.Gson
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.data.model.base.ErrorResponse
import com.newapptest.manuelperera.newapptest.data.model.base.SuccessResponse
import com.newapptest.manuelperera.newapptest.domain.model.base.ApiCodes.NO_MORE_DATA_CODE
import com.newapptest.manuelperera.newapptest.domain.model.base.ApiCodes.UNAUTHOURIZED_REQUEST_CODE
import com.newapptest.manuelperera.newapptest.domain.model.base.ApiCodes.UNKNOWN_ERROR_CODE
import com.newapptest.manuelperera.newapptest.domain.model.base.Failure
import com.newapptest.manuelperera.newapptest.domain.model.base.ResponseObject
import dagger.Lazy
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.adapter.rxjava2.Result
import timber.log.Timber
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Inject

open class BaseRemoteDataSource {

    @Inject
    protected lateinit var resources: Lazy<Resources>

    private val timeoutTime = 25L
    private val retryTimes = 3

    fun <RO : ResponseObject<DO>, DO : Any> modifySingle(single: Single<Result<RO>>): Single<DO> =
        single.flatMap { data ->
            Single.create<DO> { observer ->

                if (data.response() == null) Timber.e(data.error().toString())

                data.response()?.let { response ->
                    val body: RO? = response.body()
                    val code = response.code()
                    val errorBody = response.errorBody()

                    when {
                        response.isSuccessful && body != null -> observer.onSuccess(getDomainObject(body))
                        response.isSuccessful && body == null -> observer.onSuccess(getDomainObjectNoResponse(code))
                        code == UNAUTHOURIZED_REQUEST_CODE -> observer.onError(Failure.Unauthorized())
                        code == NO_MORE_DATA_CODE -> observer.onError(Failure.NoMoreData())
                        response.isSuccessful.not() -> observer.onError(getFailureErrorWithErrorResponse(errorBody))
                        else -> observer.onError(getFailureUnknownError())
                    }

                } ?: observer.onError(getFailureError(data.error()))

            }
        }.timeout(timeoutTime, SECONDS, Single.create<DO> { subscriber ->
            subscriber.onError(getFailureTimeout())
        }).retry { count, throwable ->
            count <= retryTimes && throwable is Failure.Timeout
        }

    fun <RO : ResponseObject<DO>, DO : Any> modifySingleList(single: Single<Result<List<RO>>>): Single<List<DO>> =
        single.flatMap { data ->
            Single.create<List<DO>> { observer ->

                if (data.response() == null) Timber.e(data.error().toString())

                data.response()?.let { response ->
                    val body: List<RO>? = response.body()
                    val code = response.code()
                    val errorBody = response.errorBody()

                    when {
                        response.isSuccessful && body != null -> observer.onSuccess(getDomainObjectList(body))
                        response.isSuccessful && body == null -> observer.onSuccess(getDomainObjectNoResponse(code))
                        code == UNAUTHOURIZED_REQUEST_CODE -> observer.onError(Failure.Unauthorized())
                        code == NO_MORE_DATA_CODE -> observer.onError(Failure.NoMoreData())
                        response.isSuccessful.not() -> observer.onError(getFailureErrorWithErrorResponse(errorBody))
                        else -> observer.onError(getFailureUnknownError())
                    }

                } ?: observer.onError(getFailureError(data.error()))

            }
        }.timeout(timeoutTime, SECONDS, Single.create<List<DO>> { subscriber ->
            subscriber.onError(getFailureTimeout())
        }).retry { count, throwable ->
            count <= retryTimes && throwable is Failure.Timeout
        }

    @Suppress("UNCHECKED_CAST")
    private fun <RO : ResponseObject<DO>, DO : Any> getDomainObject(body: RO): DO =
        (body as ResponseObject<Any>).toAppDomain() as DO

    @Suppress("UNCHECKED_CAST")
    private fun <RO : ResponseObject<DO>, DO : Any> getDomainObjectList(body: List<RO>): List<DO> =
        (body as List<ResponseObject<Any>>).map { it.toAppDomain() } as List<DO>

    @Suppress("UNCHECKED_CAST")
    private fun <DO : Any> getDomainObjectNoResponse(code: Int): DO =
        SuccessResponse(code).toAppDomain() as DO

    private fun getFailureErrorWithErrorResponse(errorBody: ResponseBody?): Failure.Error {
        val errorInfo = parseErrorResponse(errorBody)
        return Failure.Error(errorInfo.code, errorInfo.message)
    }

    private fun getFailureError(throwable: Throwable?): Failure.Error = Failure.Error(
        when (throwable) {
            is UnknownHostException -> resources.get().getString(R.string.no_internet)
            else -> throwable?.message ?: resources.get().getString(R.string.unknown_error)
        }
    )

    private fun getFailureUnknownError(): Failure.Error =
        Failure.Error(resources.get().getString(R.string.unknown_error))

    private fun getFailureTimeout(): Failure.Timeout =
        Failure.Timeout(resources.get().getString(R.string.timeout_message))

    private fun parseErrorResponse(responseBody: ResponseBody?): ErrorResponse =
        responseBody?.let { Gson().fromJson(it.string(), ErrorResponse::class.java) }
            ?: ErrorResponse(UNKNOWN_ERROR_CODE, resources.get().getString(R.string.unknown_error))

}