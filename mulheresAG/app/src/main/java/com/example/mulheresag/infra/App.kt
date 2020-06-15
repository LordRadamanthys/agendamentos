package com.example.mulheresag.infra

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.mulheresag.data.remote.model.UserModel
import okhttp3.OkHttpClient


class App : Application() {
    companion object {
        lateinit var instance: App
        var ip = "http://192.168.15.9:"
        var isAdmin = false
        var userName = ""
        lateinit var preference: SharedPreferences
        var user = UserModel()
        lateinit var tokenFirebase: String
        var userToken: String =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNTkwNTM5NTkyLCJleHAiOjE1OTA2MjU5OTJ9.kEAiK1cEHkw6ncyGO7Fge5VLcNn3PwIO65vbVGSk1FM"
        var restClient = RestClient.restClient.getInstance()
//
//        fun setPreferences(context: Context, user: UserModel) {
//            preference = context.applicationContext.getSharedPreferences("user", 0)
//            preference.edit().putString("name", user.name)
//            preference.edit().putInt("id", user.id)
//            preference.edit().putString("email", user.email)
//            preference.edit().putString("token", user.token)
//            preference.edit().putBoolean("admin", user.admin)
//        }

        fun setPreferences(context: Context): SharedPreferences {
            preference = context.applicationContext.getSharedPreferences("user", 0)
            return preference
        }

        fun getPreferences(context: Context, user: UserModel) {
            val preference: SharedPreferences =
                context.applicationContext.getSharedPreferences("user", 0)
            preference.edit().putString("name", user.name)
            preference.edit().putString("email", user.email)
            preference.edit().putString("token", user.token)
            preference.edit().putBoolean("admin", user.admin)
        }

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