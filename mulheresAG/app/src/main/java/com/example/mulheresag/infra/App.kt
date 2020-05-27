package com.example.mulheresag.infra

import android.app.Application
import com.example.mulheresag.data.remote.model.UserModel
import okhttp3.OkHttpClient


class App : Application() {
    companion object {
        lateinit var instance: App
        var ip = "http://192.168.15.9:"
        var isAdmin = false
        var userName = ""
        lateinit var tokenFirebase: String
        var userToken: String =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNTkwNTM5NTkyLCJleHAiOjE1OTA2MjU5OTJ9.kEAiK1cEHkw6ncyGO7Fge5VLcNn3PwIO65vbVGSk1FM"
        var restClient = RestClient.restClient.getInstance()

    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        restClient = RestClient.restClient.getInstance()

    }

    object app {

        fun getRestClient(): RestClient {
            return restClient
        }
    }

    val okHttpClient = OkHttpClient.Builder()
        .authenticator { route, response ->
            val credential = App.userToken
            response.request().newBuilder()
                .header("Authorization", credential)
                .build()
        }
        .build()

}