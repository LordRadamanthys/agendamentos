package com.example.mulheresag.infra

import android.app.Application


class App : Application() {
    companion object {
        lateinit var instance: App
        lateinit var restClient: RestClient
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        restClient = RestClient.instance
    }

    object app {
        fun getInstance(): App {
            return instance
        }

        fun getRestClient():RestClient{
            return restClient
        }
    }

}