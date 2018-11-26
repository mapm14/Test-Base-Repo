package com.newapptest.manuelperera.newapptest.domain.usecase.base

import io.reactivex.Single

interface UseCase<Type, in Params> {

    operator fun invoke(params: Params): Single<Type>

}