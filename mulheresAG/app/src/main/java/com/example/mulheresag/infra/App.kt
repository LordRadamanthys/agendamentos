package com.example.mulheresag.infra

import android.app.Application


class App : Application() {
    companion object {
        lateinit var instance: App
        lateinit var tokenFirebase: String
         var userToken: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNTg4OTkzNTQ3LCJleHAiOjE1ODkwNzk5NDd9.mR5wLU_SAuCydWS2w754SiuTJxrbfhQrb3JT_M9lIyc"
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