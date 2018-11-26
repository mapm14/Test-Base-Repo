package com.newapptest.manuelperera.newapptest.data.datasources.local.base

import io.reactivex.Completable
import io.reactivex.Single

interface Database<T> {

    fun save(data: T) : Single<T>

    fun saveAll(data: List<T>) : Single<List<T>>

    fun get(id: String) : Single<T>

    fun getAll() : Single<List<T>>

    fun delete(id: String) : Completable

    fun deleteAll() : Completable

}