package com.example.mulheresag.data.remote

import com.example.mulheresag.data.remote.model.CreateReservationModel
import com.example.mulheresag.data.remote.model.ReservationModel
import retrofit2.Call
import retrofit2.http.*

interface ReservationAPI {

    @GET("getUserReservations")
    fun getReservation(@Header("Authorization") token: String): Call<ArrayList<ReservationModel>>

    @POST("newReservation")
    fun createReservation(@Header("Authorization") toke:String, @Body model: ReservationModel):Call<ReservationModel>

}