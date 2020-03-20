package com.example.mulheresag.infra

import android.app.Application


class App : Application() {
    companion object {
        lateinit var instance: App
        lateinit var tokenFirebase: String
         var userToken: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNTg0Njc0NjMyLCJleHAiOjE1ODQ3NjEwMzJ9.YTYBqZe0ds42J1wXSbl0i1O9G9nDpqJv41ksyLN7Gps"
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