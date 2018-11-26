package com.newapptest.manuelperera.newapptest.domain.model.base

interface ResponseObject<out DomainObject : Any> {

    fun toAppDomain(): DomainObject

}