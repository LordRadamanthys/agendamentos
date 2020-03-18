package com.example.mulheresag.infra

import android.app.Application


class App : Application() {
    companion object {
        lateinit var instance: App
         var restClient=RestClient.restClient.getInstance()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        restClient = RestClient.restClient.getInstance()
    }

    object app {


        fun getRestClient():RestClient{
            return restClient
        }
    }

}