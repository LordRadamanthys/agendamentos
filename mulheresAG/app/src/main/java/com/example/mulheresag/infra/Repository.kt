package com.example.mulheresag.infra

import java.lang.reflect.GenericDeclaration
import java.util.*

open class Repository {
   var data:RepositoryData

    init {
        this.data = RepositoryData()
    }
    class RepositoryData{
        fun <T> restApi(type:Class<T>): T {
            return App.app.getRestClient().createService(type)
        }
    }
}