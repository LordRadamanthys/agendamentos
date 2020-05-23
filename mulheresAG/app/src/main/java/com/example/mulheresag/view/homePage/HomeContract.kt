package com.example.mulheresag.view.homePage

import com.example.mulheresag.data.remote.model.ReservationModel

class HomeContract {
    interface View {
        fun setList(list: ArrayList<ReservationModel>)
        fun showError(text: String)
        fun showProgressBar(key: Boolean)
    }


    interface Presenter {
        fun getListReservations(id: Int = -1)
    }
}