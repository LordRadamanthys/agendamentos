package com.example.mulheresag.data.remote

import com.example.mulheresag.data.remote.model.ReservationModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ReservationAPI {

    @GET("reservation")
    fun getReservation(
        @Header("id") id: Int,
        @Header("Authorization") token: String
    ): Call<ArrayList<ReservationModel>>

    @POST("reservation")
    fun createReservation(
        @Header("Authorization") toke: String,
        @Body model: ReservationModel
    ): Call<ReservationModel>

}