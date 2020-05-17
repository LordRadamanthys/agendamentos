package com.example.mulheresag.infra

import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import com.squareup.okhttp.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class RestClient {
    companion object {
        lateinit var instance: RestClient
        lateinit var retrofit: Retrofit
    }

    init {
        var gsonBuilder = GsonBuilder()
        retrofit = Retrofit.Builder()
            .baseUrl(App.ip+"3333/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        Date::class.java,
                        DateTypeAdapter()
                    ).create()
                )
            ).build()
    }

    object restClient {
        fun getInstance(): RestClient {

            instance = RestClient()
            return instance

        }

    }


    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }


}