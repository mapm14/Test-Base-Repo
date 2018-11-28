package com.newapptest.manuelperera.newapptest.domain.usecase.base

import com.newapptest.manuelperera.newapptest.data.model.base.Results

interface UseCase<Type, in Params> {

    suspend operator fun invoke(params: Params): Results

}