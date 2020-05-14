package com.example.mulheresag.data.remote

import com.example.mulheresag.data.remote.model.ServiceModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ServicesAPI {

    @GET("services")
    fun getAllServices(@Header("Authorization") token: String): Call<ArrayList<ServiceModel>>
}