package com.example.mulheresag.view.homePage

import com.example.mulheresag.data.remote.model.ReservationModel

class HomeContract {
    interface View{
        fun setList(list:ArrayList<ReservationModel>)
    }


    interface Presenter{
        fun getListReservations()
    }
}