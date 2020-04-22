package com.example.mulheresag.data.remote.model

class ReservationModel {
    lateinit var hour: String
    lateinit var date: String
    lateinit var description: String
    lateinit var status: String
    var fullPrice: Double = 0.0
    var services: ArrayList<ServiceModel>? = null


}