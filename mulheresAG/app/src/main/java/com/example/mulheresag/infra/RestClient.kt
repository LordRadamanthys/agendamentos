package com.example.mulheresag.infra

import com.example.mulheresag.infra.RestClient.restClient.initClient
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.GenericDeclaration
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
            .baseUrl("")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        Date::class.java,
                        DateTypeAdapter()
                    ).create()
                )
            ).client(initClient())
            .build()
    }
object restClient{
    fun getInstance():RestClient{
        if(instance==null){
            instance= RestClient()
        }
        return instance

    }
    fun initClient():OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(3,TimeUnit.MINUTES)
            .readTimeout(3,TimeUnit.MINUTES)
            .writeTimeout(3,TimeUnit.MINUTES)
            .build()
    }
}



    fun <T> createService(serviceClass:Class<T> ):T{
        return retrofit.create(serviceClass)
    }



}