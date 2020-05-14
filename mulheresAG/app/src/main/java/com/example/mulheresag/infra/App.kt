package com.example.mulheresag.infra

import android.app.Application


class App : Application() {
    companion object {
        lateinit var instance: App
        lateinit var tokenFirebase: String
         var userToken: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNTg5MTQxNDkwLCJleHAiOjE1ODkyMjc4OTB9.Jw2EUfxDG8eFykzkDXz7dguqcJoaZ4AS0u6LOSL1ZVs"
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