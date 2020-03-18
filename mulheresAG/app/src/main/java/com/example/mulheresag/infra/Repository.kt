package com.example.mulheresag.infra

import java.lang.reflect.GenericDeclaration
import java.util.*

open class Repository {
    lateinit final var data:RepositoryData

    class RepositoryData{
        fun <T> restApi(type:Class<T>): T {
            return App.app.getRestClient().createService(type)
        }
    }
}