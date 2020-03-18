package com.example.mulheresag.data.remote.model

class ReservationModel {
     var hour: String = ""
     var date: String = ""
     var description: String = ""
     var status: String = ""

    constructor(hour: String, date: String, description: String, status: String) {
        this.date = date
        this.hour = hour
        this.description = description
        this.status = status
    }


}