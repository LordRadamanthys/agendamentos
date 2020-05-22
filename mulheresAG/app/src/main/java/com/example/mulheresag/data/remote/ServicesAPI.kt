package com.example.mulheresag.data.remote

import com.example.mulheresag.data.remote.model.ServiceModel
import retrofit2.Call
import retrofit2.http.*

interface ServicesAPI {

    @GET("services")
    fun getAllServices(@Header("Authorization") token: String): Call<ArrayList<ServiceModel>>

    @GET("service/{id}")
    fun getService(@Path("id") int:Int, @Header("Authorization") token: String): Call<ServiceModel>


    @POST("service")
    fun createService(@Body model:ServiceModel, @Header("Authorization")token:String):Call<ServiceModel>

    @PUT("service")
    fun updateService(@Body model:ServiceModel, @Header("Authorization")token:String):Call<String>
}