package com.example.mulheresag.infra

import android.app.Application


class App : Application() {
    companion object {
        lateinit var instance: App
        lateinit var tokenFirebase: String
         var userToken: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNTg3NTI1MTc3LCJleHAiOjE1ODc2MTE1Nzd9.m8fC_5jqCKThy-7eXPmTeHVDPzNkB6g-gyB-t4DqtCI"
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