package com.example.mulheresag.infra

interface BaseCallBack<T> {
    fun onSuccessful(value: T)
    fun onUnsuccessful(error:String)
}