package com.example.mulheresag.infra

import android.app.Application


class App : Application() {
    companion object {
        lateinit var instance: App
        lateinit var tokenFirebase: String
         var userToken: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNTg0ODQxOTYwLCJleHAiOjE1ODQ5MjgzNjB9.a9Vqa-ljTFDgsmJF6ggGdOvkkFIpATd05d_YX_A4hDA"
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