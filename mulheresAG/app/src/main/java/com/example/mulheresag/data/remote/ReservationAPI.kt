package com.example.mulheresag.data.remote

import com.example.mulheresag.data.remote.model.ReservationModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ReservationAPI {

    @GET("getUserReservations")
    fun getReservation(@Header("Authorization") token:String):Call< ArrayList< ReservationModel>>

}