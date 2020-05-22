package com.example.mulheresag.data.remote

import com.example.mulheresag.data.remote.model.ServiceModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ServicesAPI {

    @GET("services")
    fun getAllServices(@Header("Authorization") token: String): Call<ArrayList<ServiceModel>>


    @POST("service")
    fun createService(@Body model:ServiceModel, @Header("Authorization")token:String):Call<ServiceModel>
}